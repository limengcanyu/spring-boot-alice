package com.spring.boot.mybatisplus.druid.multidatasource.config;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * <p>Description: </p>
 *
 * @author Rock Jiang
 * @version 1.0
 * @date 2018/5/24 0024
 */
@Component
@Aspect
@Order(-100) //这是为了保证AOP在事务注解之前生效,Order的值越小,优先级越高
public class DataSourceSwitchAspect {

    @Pointcut("execution(* com.spring.boot.druid.multidatasource.artanis.dao.mapper.*.*(..))")
    private void dbArtanisAspect() {
    }

    @Pointcut("execution(* com.spring.boot.druid.multidatasource.samuro.dao.mapper.*.*(..))")
    private void dbSamuroAspect() {
    }

    @Pointcut("execution(* com.spring.boot.druid.multidatasource.gt1.dao.mapper.*.*(..))")
    private void dbGt1Aspect() {
    }

    /**
     * 在执行Mapper中的方法之前切换到指定数据源
     */
    @Before( "dbArtanisAspect()" )
    public void dbArtanis() {
        System.out.println("切换到数据源 阿塔尼斯");
        DbContextHolder.setDbType(DBTypeEnum.dbArtanis);
    }

    @Before( "dbSamuroAspect()" )
    public void dbSamuro() {
        System.out.println("切换到数据源 萨穆罗");
        DbContextHolder.setDbType(DBTypeEnum.dbSamuro);
    }

    @Before("dbGt1Aspect()" )
    public void dbGt1 () {
        System.out.println("切换到数据源 GT1");
        DbContextHolder.setDbType(DBTypeEnum.dbGt1);
    }
}
