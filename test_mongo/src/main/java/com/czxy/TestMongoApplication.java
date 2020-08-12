package com.czxy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Author JiaLe Pei
 * @Date 2020/6/12 16:02
 * @Version 1.0
 */
@SpringBootApplication
@ComponentScan(basePackages = "com.czxy.dao")
public class TestMongoApplication {
    public static void main(String[] args) {
        SpringApplication.run(TestMongoApplication.class,args);
    }
}
