package com.spring.boot.tomcat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class SpringBootTomcatApplication {
    private static final Logger logger = LoggerFactory.getLogger(SpringBootTomcatApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(SpringBootTomcatApplication.class);
        logger.debug("application started...");
    }

    @RequestMapping("")
    public String echo() {
        logger.info("hello!");
        return "hello";
    }
}
