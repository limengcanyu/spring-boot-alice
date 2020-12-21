package com.spring.boot.caffeine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class SpringBootCachingCaffeineApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootCachingCaffeineApplication.class, args);
    }

}
