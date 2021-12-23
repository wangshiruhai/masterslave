package com.crud.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import com.crud.context.DataSourceEnum;
import com.crud.context.DataSourceRouter;
import java.util.HashMap;
import java.util.Map;
import javax.sql.DataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

/**
 * @author tommy
 * @date 11/6/21
 */
@Configuration
@MapperScan(basePackages = "com.crud.mapper", sqlSessionTemplateRef = "sqlTemplate")
public class DataSourceConfig {
    /**
     * 主库
     */
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.master")
    public DataSource master() {
        return DruidDataSourceBuilder.create().build();
    }

    /**
     * 从库
     */
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.slave")
    public DataSource slaver() {
        return DruidDataSourceBuilder.create().build();
    }


    /**
     * 实例化数据源路由
     */
    @Bean
    public DataSourceRouter dynamicDB(@Qualifier("master") DataSource masterDataSource,
            @Autowired(required = false) @Qualifier("slaver") DataSource slaveDataSource) {
        DataSourceRouter dynamicDataSource = new DataSourceRouter();
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put(DataSourceEnum.MASTER.getDataSourceName(), masterDataSource);
        if (slaveDataSource != null) {
            targetDataSources.put(DataSourceEnum.SLAVE.getDataSourceName(), slaveDataSource);
        }
        dynamicDataSource.setTargetDataSources(targetDataSources);
        dynamicDataSource.setDefaultTargetDataSource(masterDataSource);
        return dynamicDataSource;
    }


    /**
     * 配置sessionFactory
     * @param dynamicDataSource
     * @return
     * @throws Exception
     */
    @Bean
    public SqlSessionFactory sessionFactory(@Qualifier("dynamicDB") DataSource dynamicDataSource) throws Exception {
        //此处使用 MybatisSqlSessionFactoryBean 而不是 SqlSessionFactoryBean
        MybatisSqlSessionFactoryBean factory = new MybatisSqlSessionFactoryBean();
        factory.setDataSource(dynamicDataSource);
        return factory.getObject();
    }

    /**
     * 创建sqlTemplate
     * @param sqlSessionFactory
     * @return
     */
    @Bean
    public SqlSessionTemplate sqlTemplate(@Qualifier("sessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }


    /**
     * 事务配置
     *
     * @param dynamicDataSource
     * @return
     */
    @Bean(name = "dataSourceTx")
    public DataSourceTransactionManager dataSourceTransactionManager(@Qualifier("dynamicDB") DataSource dynamicDataSource) {
        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
        dataSourceTransactionManager.setDataSource(dynamicDataSource);
        return dataSourceTransactionManager;
    }
}
