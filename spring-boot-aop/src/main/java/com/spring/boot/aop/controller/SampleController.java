package com.spring.boot.aop.controller;

import com.spring.boot.aop.service.SampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleController {

    @Autowired
    private SampleService sampleService;

    /**
     * localhost:8080/compute
     *
     * @return
     */
    @RequestMapping("/compute")
    public String compute() throws Exception {
        return sampleService.compute();
//        throw new Exception();
    }
}
