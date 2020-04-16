package com.spring.boot.aop.config;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Aspect
@Order(-100) //这是为了保证AOP在事务注解之前生效,Order的值越小,优先级越高
public class ControllerAspect {

    @Pointcut("execution(* com.spring.boot.aop.controller.SampleController.*(..))")
    private void doComputeAspect() {
    }

    @Before("doComputeAspect()")
    public void doComputeBefore() {
        log.debug("=== ControllerAspect do Compute Before");
    }

    @AfterReturning("doComputeAspect()")
    public void doComputeAfterReturning() {
        log.debug("=== ControllerAspect do Compute After Returning");
    }

    @AfterThrowing("doComputeAspect()")
    public void doComputeAfterThrowing() {
        log.debug("=== ControllerAspect do Compute After Throwing");
    }

    @Around("doComputeAspect()")
    public Object doComputeAround(ProceedingJoinPoint pjp) throws Throwable {
        log.debug("=== ControllerAspect do Compute Around");

        // start stopwatch
        Object retVal = pjp.proceed();

        // 遇到异常不会执行以下语句
        log.debug("=== ControllerAspect do Compute Around return value: {}", retVal);

        // stop stopwatch
        return retVal;
    }

}
