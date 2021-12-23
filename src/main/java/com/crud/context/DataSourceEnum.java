package com.crud.context;

import lombok.Getter;

/**
 * @author tommy
 * @date 11/6/21
 */
@Getter
public enum DataSourceEnum {
    MASTER,
    SLAVE;

    public String getDataSourceName(){
        return this.name();
    }
}
