package com.spring.boot.multi.datasource.transaction.aop;

import com.spring.boot.multi.datasource.transaction.annotation.MultiTransactional;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.util.*;

/**
 * 多数据源事务切面
 *
 */
@Slf4j
@Aspect
@Component
public class MultiTransactionDynamicAop {

    @Autowired
    private ApplicationContext applicationContext;

    /**
     * 事务声明
     */
    private final DefaultTransactionDefinition def = new DefaultTransactionDefinition();

    static {
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        // explicitly setting the transaction name is something that can be done only programmatically
//        def.setName("SomeTxName");
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        def.setIsolationLevel(TransactionDefinition.ISOLATION_DEFAULT);
    }

    @Pointcut("@annotation(com.spring.boot.multi.datasource.transaction.annotation.MultiTransactional)")
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
    public Object doBasicProfiling(ProceedingJoinPoint pjp, MultiTransactional multiTransactional) throws Throwable {
        log.debug("多数据源事务处理 开始......");

        Deque<Map<PlatformTransactionManager, TransactionStatus>> transactionManagers = new ArrayDeque<>();

        String[] transactionManagerList = multiTransactional.transactionManager();

        for (String s : transactionManagerList) {
            PlatformTransactionManager transactionManager = applicationContext.getBean(s, PlatformTransactionManager.class);
            TransactionStatus transactionStatus = transactionManager.getTransaction(def);
            Map<PlatformTransactionManager, TransactionStatus> map = new HashMap<>();
            map.put(transactionManager, transactionStatus);
            transactionManagers.push(map);
        }

        Object retVal;
        try {
            // start stopwatch
            retVal = pjp.proceed();
        } catch (Exception ex) {
            for (int i = 0; i < transactionManagers.size(); i++) {
                Map<PlatformTransactionManager, TransactionStatus> map = transactionManagers.pop();
                PlatformTransactionManager transactionManager = (PlatformTransactionManager) map.keySet().toArray()[0];
                TransactionStatus transactionStatus = map.get(transactionManager);
                transactionManager.rollback(transactionStatus);
            }

            throw ex;
        }

        for (int i = 0; i < transactionManagers.size(); i++) {
            Map<PlatformTransactionManager, TransactionStatus> map = transactionManagers.pop();
            PlatformTransactionManager transactionManager = (PlatformTransactionManager) map.keySet().toArray()[0];
            TransactionStatus transactionStatus = map.get(transactionManager);
            transactionManager.commit(transactionStatus);
        }

        log.debug("多数据源事务处理 结束......");
        // stop stopwatch
        return retVal;
    }
}
