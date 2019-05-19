package com.spring.boot.mybatisplus.druid.dynamic.datasource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
//@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, DruidDataSourceAutoConfigure.class})
public class DruidDynamicDatasourceApplication {
    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(DruidDynamicDatasourceApplication.class);

//        System.out.println("BeanDefinitionCount: " + ctx.getBeanDefinitionCount());
//
//        String[] beanDefinitionNames = ctx.getBeanDefinitionNames();
//        for (String name : beanDefinitionNames) {
//            System.out.println("BeanDefinitionName: " + name);
//        }
//
//        System.out.println(ctx.getBean("dataSource").getClass());
//        System.out.println(ctx.getBean(DruidDataSource.class));
//
//        DruidDataSource dataSource = ctx.getBean(DruidDataSource.class);

//        Properties connectProperties = dataSource.getConnectProperties();
//        System.out.println("connectProperties: " + connectProperties);
//
//        String properties = dataSource.getProperties();
//        System.out.println("properties: " + properties);

//        String initStackTrace = dataSource.getInitStackTrace();
//        System.out.println("initStackTrace: " + initStackTrace);
//
//        String jdbcUrl = dataSource.getUrl();
//        System.out.println("jdbcUrl: " + jdbcUrl);

//        String targetJdbcUrl = jdbcUrl.replace("artanis", "alita");
//        System.out.println("targetJdbcUrl: " + targetJdbcUrl);
//        dataSource.setUrl(targetJdbcUrl);

//        jdbcUrl = dataSource.getUrl();
//        System.out.println("jdbcUrl: " + jdbcUrl);


    }
}
