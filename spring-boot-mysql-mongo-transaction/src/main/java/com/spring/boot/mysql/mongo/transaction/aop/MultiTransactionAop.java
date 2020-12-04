package com.spring.boot.mysql.mongo.transaction.aop;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import com.spring.boot.mysql.mongo.transaction.annotation.MultiTransactional;
import com.spring.boot.mysql.mongo.transaction.dao.entity.PlatformSalaryItem;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

/**
 * 多数据源事务切面
 * ※采用Around似乎不行※
 *
 */
@Slf4j
@Component
@Aspect
public class MultiTransactionAop {
    @Autowired
    private DataSourceTransactionManager dataSourceTransactionManager;

    @Autowired
    private MongoTransactionManager mongoTransactionManager;

//    /**
//     * 线程本地变量：为什么使用栈？※为了达到后进先出的效果※
//     */
//    private static final ThreadLocal<Stack<Map<DataSourceTransactionManager, TransactionStatus>>> THREAD_LOCAL = new ThreadLocal<>();
//
//    /**
//     * 用于获取事务管理器
//     */
//    @Autowired
//    private ApplicationContext applicationContext;
//
//    /**
//     * 事务声明
//     */
//    private DefaultTransactionDefinition def = new DefaultTransactionDefinition();
//    {
//        // 非只读模式
//        def.setReadOnly(false);
//        // 事务隔离级别：采用数据库的
//        def.setIsolationLevel(TransactionDefinition.ISOLATION_DEFAULT);
//        // 事务传播行为
//        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
//    }

    @Pointcut("@annotation(com.spring.boot.mysql.mongo.transaction.annotation.MultiTransactional)")
    public void pointcut() {
    }

    @Around("@annotation(com.spring.boot.mysql.mongo.transaction.annotation.MultiTransactional)")
    public Object doBasicProfiling(ProceedingJoinPoint pjp) throws Throwable {
        log.debug("多数据源事务处理 开始...");

        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        // explicitly setting the transaction name is something that can be done only programmatically
        def.setName("SomeTxName");
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);

        TransactionStatus mysqlStatus = dataSourceTransactionManager.getTransaction(def);
        TransactionStatus mongoStatus = mongoTransactionManager.getTransaction(def);

        Object retVal;
        try {
            // start stopwatch
            retVal = pjp.proceed();
        } catch (Exception ex) {
            mongoTransactionManager.rollback(mongoStatus);
            dataSourceTransactionManager.rollback(mysqlStatus);
            throw ex;
        }

        mongoTransactionManager.commit(mongoStatus);
        dataSourceTransactionManager.commit(mysqlStatus);

        log.debug("多数据源事务处理 结束...");
        // stop stopwatch
        return retVal;
    }

//    /**
//     * 声明事务
//     *
//     * @param transactional 注解
//     */
//    @Before("pointcut() && @annotation(transactional)")
//    public void before(MultiTransactional transactional) {
//        // 根据设置的事务名称按顺序声明，并放到ThreadLocal里
//        String[] transactionManagerNames = transactional.transactionManagers();
//        Stack<Map<DataSourceTransactionManager, TransactionStatus>> pairStack = new Stack<>();
//
//        for (String transactionManagerName : transactionManagerNames) {
//            DataSourceTransactionManager transactionManager = applicationContext.getBean(transactionManagerName, DataSourceTransactionManager.class);
//            TransactionStatus transactionStatus = transactionManager.getTransaction(def);
//            Map<DataSourceTransactionManager, TransactionStatus> transactionMap = new HashMap<>();
//            transactionMap.put(transactionManager, transactionStatus);
//            pairStack.push(transactionMap);
//        }
//
//        THREAD_LOCAL.set(pairStack);
//    }
//
//    /**
//     * 提交事务
//     */
//    @AfterReturning("pointcut()")
//    public void afterReturning() {
//        // ※栈顶弹出（后进先出）
//        Stack<Map<DataSourceTransactionManager, TransactionStatus>> pairStack = THREAD_LOCAL.get();
//
//        while (!pairStack.empty()) {
//            Map<DataSourceTransactionManager, TransactionStatus> pair = pairStack.pop();
//            pair.forEach((key,value)->key.commit(value));
//        }
//
//        THREAD_LOCAL.remove();
//    }
//
//    /**
//     * 回滚事务
//     */
//    @AfterThrowing(value = "pointcut()")
//    public void afterThrowing() {
//        // ※栈顶弹出（后进先出）
//        Stack<Map<DataSourceTransactionManager, TransactionStatus>> pairStack = THREAD_LOCAL.get();
//        while (!pairStack.empty()) {
//            Map<DataSourceTransactionManager, TransactionStatus> pair = pairStack.pop();
//            pair.forEach((key,value)->key.rollback(value));
//        }
//
//        THREAD_LOCAL.remove();
//    }
}
