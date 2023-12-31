package com.spring.boot.tomcat;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@SpringBootApplication
public class SpringBootTomcatApplication {
    private static final Logger logger = LoggerFactory.getLogger(SpringBootTomcatApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(SpringBootTomcatApplication.class);
        logger.debug("application started...");
    }

    /**
     * localhost:8070/echo
     *
     * @return
     */
    @RequestMapping("/echo")
    public String echo() {
        log.debug("this is tomcat app echo");
        return "this is tomcat app";
    }
}
