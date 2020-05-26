package com.spring.boot.swagger2.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * description:
 *
 * @author rock
 * time 2020/5/26 0026 16:40
 */
@Api("EchoController")
@RestController
public class EchoController {
    @RequestMapping("/echo")
    public String echo() {
        return "this is echo method of EchoController";
    }
}
