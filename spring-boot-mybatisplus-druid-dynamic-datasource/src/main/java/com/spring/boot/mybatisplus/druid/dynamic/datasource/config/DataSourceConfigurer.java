package com.spring.boot.mybatisplus.druid.dynamic.datasource.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class DataSourceConfigurer {
    private static final Logger LOGGER = LoggerFactory.getLogger(DataSourceConfigurer.class);

    @Primary
    @Bean
    public DataSource dataSource() {
        return DruidDataSourceBuilder.create().build();
    }

    /**
     * 注册动态数据源
     *
     * @return
     */
    @Bean("dynamicDataSource")
    public DynamicRoutingDataSource dynamicDataSource() {
        Map<Object, Object> dataSourceMap = new HashMap<>();
        dataSourceMap.put("default_db", dataSource());

        DynamicRoutingDataSource dynamicRoutingDataSource = new DynamicRoutingDataSource();
        dynamicRoutingDataSource.setDefaultTargetDataSource(dataSource());
        dynamicRoutingDataSource.setTargetDataSources(dataSourceMap);

        return dynamicRoutingDataSource;
    }
}
