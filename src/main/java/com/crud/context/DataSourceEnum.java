package com.crud.context;

import lombok.Getter;

/**
 * @author tommy
 * @date 11/6/21
 */
@Getter
public enum DataSourceEnum {
    /**
     * 主库数据来源
     */
    MASTER,
    /**
     * 从库1数据来源
     */
    SLAVE1,
    /**
     * 从库2数据来源
     */
    SLAVE2,
    /**
     * 标签
     */
    SLAVE;

    public String getDataSourceName(){
        return this.name();
    }
}
