package com.spring.boot.sentinel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *
 * </p>
 *
 * @author rock.jxf
 * @date 2020/11/24 14:24
 */
@RestController
public class TestController {

    @Autowired
    private TestService service;

    /**
     * localhost:8080/hello/jessica
     *
     * @param name
     * @return
     */
    @GetMapping(value = "/hello/{name}")
    public String apiHello(@PathVariable String name) {
        return service.sayHello(name);
    }
}
