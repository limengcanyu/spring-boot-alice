package com.spring.boot.mybatisplus.druid.dynamic.datasource.configuration;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@MapperScan("com.spring.boot.mybatisplus.druid.dynamic.datasource.mapper")
@Configuration
public class MybatisPlusConfig {

    /**
     * 动态数据源配置
     *
     * @return
     */
    @Bean
    public DynamicRoutingDataSource dynamicDataSource() {
        DruidDataSource dataSource = DruidDataSourceBuilder.create().build();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://192.168.31.128:3306/artanis?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC&characterEncoding=utf8");
        dataSource.setUsername("root");
        dataSource.setPassword("Gaohang321");

        DynamicRoutingDataSource dynamicDataSource = new DynamicRoutingDataSource();

        TargetDataSourcesMap.targetDataSources.put("defaultTargetDataSource", dataSource);

        //目标数据源
        dynamicDataSource.setTargetDataSources(TargetDataSourcesMap.targetDataSources);
        //默认数据源
        dynamicDataSource.setDefaultTargetDataSource(dataSource);

        return dynamicDataSource;
    }

}
