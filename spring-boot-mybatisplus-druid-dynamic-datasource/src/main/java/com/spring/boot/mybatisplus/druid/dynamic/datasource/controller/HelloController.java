package com.spring.boot.mybatisplus.druid.dynamic.datasource.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @RequestMapping("/hello")
    public String hello() {
        System.out.println("HelloController hello");

        return "hello";
    }
}
