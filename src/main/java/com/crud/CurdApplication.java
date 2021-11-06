package com.crud;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.dzh.mapper")
@SpringBootApplication
public class CurdApplication {

    public static void main(String[] args) {
        SpringApplication.run(CurdApplication.class, args);
    }

}
