package com.spring.boot.aop.config;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Aspect
@Order(-100) //这是为了保证AOP在事务注解之前生效,Order的值越小,优先级越高
public class ControllerMethodAspect {

    @Pointcut("execution(* com.spring.boot.aop.controller.SampleController.compute(..))")
    private void doComputeAspect() {
    }

    @Before("doComputeAspect()")
    public void doComputeBefore() {
        log.debug("=== ControllerMethodAspect do Compute Before");
    }

    @AfterReturning("doComputeAspect()")
    public void doComputeAfterReturning() {
        log.debug("=== ControllerMethodAspect do Compute After Returning");
    }

    @AfterThrowing("doComputeAspect()")
    public void doComputeAfterThrowing() {
        log.debug("=== ControllerMethodAspect do Compute After Throwing");
    }

}
