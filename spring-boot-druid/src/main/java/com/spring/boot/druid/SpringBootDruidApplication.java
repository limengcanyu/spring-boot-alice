package com.spring.boot.druid;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.spring.boot.druid.mapper")
@SpringBootApplication
public class SpringBootDruidApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootDruidApplication.class, args);
    }
}
