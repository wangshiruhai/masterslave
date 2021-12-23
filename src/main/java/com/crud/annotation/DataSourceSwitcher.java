package com.crud.annotation;

import com.crud.context.DataSourceEnum;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author tommy
 * @date 11/6/21
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface DataSourceSwitcher {

    /**
     * 默认数据源
     * @return
     */
    DataSourceEnum value() default DataSourceEnum.MASTER;
    /**
     * 清除
     * @return
     */
    boolean clear() default true;
}
