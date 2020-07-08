package com.sixkery;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan(value = "com.sixkery.dao")
@SpringBootApplication
public class CodeDockerApplication {

    public static void main(String[] args) {
        SpringApplication.run(CodeDockerApplication.class, args);
    }

}
