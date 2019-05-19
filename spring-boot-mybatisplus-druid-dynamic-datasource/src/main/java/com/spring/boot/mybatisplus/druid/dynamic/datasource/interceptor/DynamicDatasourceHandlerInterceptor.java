package com.spring.boot.mybatisplus.druid.dynamic.datasource.interceptor;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.fastjson.JSONObject;
import com.spring.boot.mybatisplus.druid.dynamic.datasource.configuration.TargetDataSourcesMap;
import com.spring.boot.mybatisplus.druid.dynamic.datasource.service.DynamicDataSourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class DynamicDatasourceHandlerInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private DynamicDataSourceService dynamicDataSourceService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("DynamicDatasourceHandlerInterceptor preHandle");

        DruidDataSource newTenantDataSource = dynamicDataSourceService.getTenantDataSource("alita");

        System.out.println("TargetDataSourcesMap.targetDataSources: " + TargetDataSourcesMap.targetDataSources);

        // 获取当前租户的数据源，若不存在，则从后台获取租户的数据源，然后放在TargetDataSourcesMap中

        TargetDataSourcesMap.targetDataSources.put("alita", newTenantDataSource);
        System.out.println("TargetDataSourcesMap.targetDataSources: " + TargetDataSourcesMap.targetDataSources);

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("DynamicDatasourceHandlerInterceptor postHandle");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("DynamicDatasourceHandlerInterceptor afterCompletion");
    }
}
