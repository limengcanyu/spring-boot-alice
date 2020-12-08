package com.spring.boot.annotation.aop;

import com.spring.boot.annotation.annotation.MultiTransactional;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Stack;

/**
 * 多数据源事务切面
 */
@Slf4j
@Aspect
@Component
public class MultiTransactionAop {

    @Pointcut("@annotation(com.spring.boot.annotation.annotation.MultiTransactional)")
    public void pointcut() {
    }

//    @Before("@annotation(multiTransactional)")
//    public void beforeProcess(MultiTransactional multiTransactional) {
//        String[] transactionManagerList = multiTransactional.transactionManager();
//
//        for (String s : transactionManagerList) {
//            System.out.println(s);
//        }
//
//    }

    @Around("@annotation(multiTransactional)")
    public Object aroundProcess(ProceedingJoinPoint pjp, MultiTransactional multiTransactional) throws Throwable {
        log.debug("多数据源事务处理 开始......");

        String[] transactionManagerList = multiTransactional.transactionManager();

        for (String s : transactionManagerList) {
            System.out.println(s);
        }

        Object retVal;
        try {
            // start stopwatch
            retVal = pjp.proceed();
        } catch (Exception ex) {
            throw ex;
        }

        log.debug("多数据源事务处理 结束......");
        // stop stopwatch
        return retVal;
    }
}
