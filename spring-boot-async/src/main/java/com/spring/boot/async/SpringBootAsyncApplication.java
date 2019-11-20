package com.spring.boot.async;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;


// 启用异步支持
@EnableAsync
@SpringBootApplication
public class SpringBootAsyncApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootAsyncApplication.class, args);
	}

}
