Technology stack:
======================
- springboot(2.2.x)
- mysql(5.7)
- springMVC 
- mysql-connector-java(8)
- mybatis-plus(3.0.5)

#### one master and multi-slave
- need modify files when add slave

|  文件   |  描述  |
| :-----  | :---- |
| src/main/resources/application.yml  | 添加多从库配置 |
| src/main/java/com/crud/context/DataSourceEnum.java  | 添加从库枚举类 |
| src/main/java/com/crud/config/DataSourceConfig.java | 添加从库N的Bean配置&设置从库（targetDataSources.put） |
| src/main/java/com/crud/biz/impl/DataSourceContextAop.java | 更改从库访问随机算法(getDataSourceRandom) |
