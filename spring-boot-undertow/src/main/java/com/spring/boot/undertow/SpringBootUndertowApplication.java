package com.spring.boot.undertow;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@SpringBootApplication
public class SpringBootUndertowApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootUndertowApplication.class);
    }

    /**
     * localhost:8071/echo
     *
     * @return
     */
    @RequestMapping("/echo")
    public String echo() {
        log.debug("this is undertow app echo");
        return "this is undertow app";
    }
}
