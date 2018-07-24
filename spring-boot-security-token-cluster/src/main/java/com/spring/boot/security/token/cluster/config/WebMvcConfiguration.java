package com.spring.boot.security.token.cluster.config;

import com.spring.boot.security.token.cluster.intercepter.SecurityInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * <p>Description: Web MVC 配置类</p>
 *
 * @author Rock Jiang
 * @version 1.0
 * @date 2017/11/24 0024
 */
@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {
    /**
     * 将拦截器单独定义Bean，拦截器中依赖的自动注入Bean才会起作用
     *
     * @return
     */
    @Bean
    public SecurityInterceptor securityInterceptor() {
        return new SecurityInterceptor();
    }

    // Configure cross origin requests processing.
    // 配置跨域请求访问
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedHeaders("*")
                .allowedMethods("*")
                .allowedOrigins("*");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration registration = registry.addInterceptor(securityInterceptor());

//        // 不拦截配置
//        registration.excludePathPatterns("/login**");

        // 拦截配置
        registration.addPathPatterns("/**");
    }

}

