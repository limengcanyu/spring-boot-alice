package com.vue.element.admin.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>Description: Hello Controller</p>
 *
 * @author rock
 * date 2019/06/23
 */
@Slf4j
@RestController
public class HelloController {
    /**
     * localhost:8080
     *
     * @return
     */
    @RequestMapping("/")
    public String home() {
        log.debug("====== home");

        return "home";
    }

    /**
     * localhost:8080/hello
     *
     * @return
     */
    @RequestMapping("/hello")
    public String hello() {
        return "hello";
    }
}
