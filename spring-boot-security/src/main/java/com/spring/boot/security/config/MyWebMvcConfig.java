package com.spring.boot.security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 配置拦截器类
 */
@Configuration
public class MyWebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/*/**")
                .allowedOrigins("*")
                .allowedMethods("*")
                .allowedHeaders("*")
                .allowCredentials(true)
                .maxAge(3600);
    }

//    /**
//     * 拦截器按添加顺序依次执行
//     *
//     * @param registry
//     */
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        // Web安全拦截器 swagger-ui、注册、登录不拦截
//        registry.addInterceptor(webSecurityInterceptor).addPathPatterns("/**")
//                .excludePathPatterns(
//                        "/swagger-resources/**",
//                        "/webjars/**",
//                        "/v2/**",
//                        "/swagger-ui.html/**",
//                        UriConst.REGISTER,
//                        UriConst.LOGIN
//                );
//    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //swagger增加url映射
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
}
