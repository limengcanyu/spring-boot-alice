package com.spring.boot.mybatisplus.druid.dynamic.datasource.interceptor;

import com.spring.boot.mybatisplus.druid.dynamic.datasource.config.DynamicDataSourceContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class DynamicDatasourceHandlerInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("DynamicDatasourceHandlerInterceptor preHandle");

        DynamicDataSourceContextHolder.setDataSourceKey("alita");
        System.out.println("当前租户ID: alita");

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
