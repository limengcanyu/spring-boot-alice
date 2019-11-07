package com.veu.element.alice.admin.controller;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RequestMapping("/hello")
@RestController
public class HelloController {
    private static final Logger logger = LoggerFactory.getLogger(HelloController.class);

    @RequestMapping("/hello")
    public String hello() {
        return "hello";
    }

    @RequestMapping("/helloBody")
    public Map<String, Object> helloBody(@RequestBody Map<String, Object> paramMap) {
        logger.debug("helloBody paramMap: {}", JSONObject.toJSONString(paramMap));
        return paramMap;
    }
}
