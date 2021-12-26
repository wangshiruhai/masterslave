package com.crud.biz.impl;

import com.crud.annotation.DataSourceSwitcher;
import com.crud.context.DataSourceContextHolder;
import com.crud.context.DataSourceEnum;
import java.lang.reflect.Method;
import java.util.concurrent.atomic.AtomicInteger;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author tommy
 * @date 11/6/21
 */
@Slf4j
@Aspect
@Order(value = 1)
@Component
public class DataSourceContextAop {

    private static AtomicInteger counter = new AtomicInteger(0);

    @Around("@annotation(com.crud.annotation.DataSourceSwitcher)")
    public Object setDynamicDataSource(ProceedingJoinPoint pjp) throws Throwable {
        boolean clear = false;
        try {
            Method method = this.getMethod(pjp);
            DataSourceSwitcher dataSourceSwitcher = method.getAnnotation(DataSourceSwitcher.class);
            clear = dataSourceSwitcher.clear();
            String dataSourceRandom = getDataSourceRandom(dataSourceSwitcher.value());
            DataSourceContextHolder.set(dataSourceRandom);
            log.info("当前数据源：{}", dataSourceRandom);

            return pjp.proceed();
        } finally {
            if (clear) {
                DataSourceContextHolder.clear();
            }
        }
    }

    private Method getMethod(JoinPoint pjp) {
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        return signature.getMethod();
    }

    /**
     * 区分主从库,从库轮训访问
     * 注意:分布式场景下需要用分布式锁代替
     * @param dataSourceEnum
     * @return
     */
    private String getDataSourceRandom(DataSourceEnum dataSourceEnum) {
        if (!dataSourceEnum.getDataSourceName().equals(DataSourceEnum.SLAVE.getDataSourceName())) {
            return DataSourceEnum.MASTER.getDataSourceName();
        }
        if (counter.get() > 999999999) {
            counter = new AtomicInteger(0);
        }
        int andIncrement = counter.getAndIncrement() % 2;

        return (andIncrement == 0) ? DataSourceEnum.SLAVE1.getDataSourceName()
                : DataSourceEnum.SLAVE2.getDataSourceName();
    }


}
