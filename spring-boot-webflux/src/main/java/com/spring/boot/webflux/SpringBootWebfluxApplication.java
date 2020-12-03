package com.spring.boot.webflux;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.scheduler.Schedulers;
import reactor.tools.agent.ReactorDebugAgent;

@RestController
@SpringBootApplication
public class SpringBootWebfluxApplication {

    public static void main(String[] args) {
        ReactorDebugAgent.init(); // 初始化Global Debugging，在类加载时会装备类，因此需要先执行
        Schedulers.enableMetrics(); // enable scheduler metrics，尽可能早的执行
        SpringApplication.run(SpringBootWebfluxApplication.class, args);
    }

    @RequestMapping("")
    public String echo() {
        return "echo service";
    }
}
