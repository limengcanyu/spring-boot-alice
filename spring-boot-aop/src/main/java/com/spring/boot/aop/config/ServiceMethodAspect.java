package com.spring.boot.aop.config;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Aspect
@Order(-100) //这是为了保证AOP在事务注解之前生效,Order的值越小,优先级越高
public class ServiceMethodAspect {

    @Pointcut("execution(* com.spring.boot.aop.service.SampleService.compute(..))")
    private void doComputeAspect() {
    }

    @Before("doComputeAspect()")
    public void doComputeBefore() {
        log.debug("=== ServiceMethodAspect do Compute Before");
    }

    @AfterReturning("doComputeAspect()")
    public void doComputeAfterReturning() {
        log.debug("=== ServiceMethodAspect do Compute After Returning");
    }

    @AfterThrowing("doComputeAspect()")
    public void doComputeAfterThrowing() {
        log.debug("=== ServiceMethodAspect do Compute After Throwing");
    }

}
