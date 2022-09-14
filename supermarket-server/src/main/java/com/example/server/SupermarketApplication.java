package com.example.server;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @PROJECT_NAME: supermarket
 * @DESCRIPTION:
 * @USER: 韩冰
 * @DATE: 2022/9/14 0014 11:06
 */
@SpringBootApplication
@MapperScan("com.example.server.mapper")
public class SupermarketApplication {
    public static void main(String[] args) {
        SpringApplication.run(SupermarketApplication.class,args);
    }
}
