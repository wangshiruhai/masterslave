package com.crud.entity;

import java.io.Serializable;
import lombok.Data;

/**
 * 值班实体类
 *
 * @author makejava
 */
@Data
public class User implements Serializable {
    private Long id;
    private String name;
    private Integer age;
    private String email;
}