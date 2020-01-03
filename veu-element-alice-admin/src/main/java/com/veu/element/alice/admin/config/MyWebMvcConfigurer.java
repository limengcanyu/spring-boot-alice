package com.veu.element.alice.admin.config;

import com.veu.element.alice.admin.constant.UriConstant;
import com.veu.element.alice.admin.interceptor.WebSecurityInterceptor;
import com.veu.element.alice.admin.interceptor.WeixinSecurityInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyWebMvcConfigurer implements WebMvcConfigurer {
    @Autowired
    private WebSecurityInterceptor webSecurityInterceptor;

    @Autowired
    private WeixinSecurityInterceptor weixinSecurityInterceptor;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/*/**")
                .allowedOrigins("*")
                .allowedMethods("*")
                .allowedHeaders("*")
                .allowCredentials(true)
                .maxAge(3600);
    }


    /**
     * 拦截器按添加顺序依次执行
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // swagger-ui、注册、登录不拦截
        registry.addInterceptor(webSecurityInterceptor).addPathPatterns("/**")
                .excludePathPatterns(
                        "/swagger-resources/**",
                        "/webjars/**",
                        "/v2/**",
                        "/swagger-ui.html/**",
                        UriConstant.URI_REGISTER,
                        UriConstant.URI_LOGIN
                );
        registry.addInterceptor(weixinSecurityInterceptor);
    }

}
