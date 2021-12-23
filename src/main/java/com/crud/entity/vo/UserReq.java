package com.crud.entity.vo;

import java.io.Serializable;
import lombok.Data;

/**
 * @author tommy
 * @date 11/6/21
 */
@Data
public class UserReq implements Serializable {
    private String name;
    private Integer age;
    private String email;
}
