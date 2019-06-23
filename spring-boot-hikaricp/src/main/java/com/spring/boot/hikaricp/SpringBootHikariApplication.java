package com.spring.boot.hikaricp;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.spring.boot.hikaricp.mapper")
@SpringBootApplication
public class SpringBootHikariApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootHikariApplication.class, args);
    }
}
