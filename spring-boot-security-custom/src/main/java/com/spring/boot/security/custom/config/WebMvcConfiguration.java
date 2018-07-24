package com.spring.boot.security.custom.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
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

    // Configure cross origin requests processing.
    // 配置跨域请求访问
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedHeaders("*")
                .allowedMethods("*")
                .allowedOrigins("*");
    }

//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        InterceptorRegistration registration = registry.addInterceptor(new SecurityInterceptor());
//
//        // 不拦截配置
//        registration.excludePathPatterns("/login**");
//
//        // 拦截配置
//        registration.addPathPatterns("/**");
//    }

}

