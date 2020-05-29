package com.spring.boot.security.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import static org.springframework.security.config.Customizer.withDefaults;

@Slf4j
public class CustomWebSecurityConfigurer extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        log.debug("====== CustomWebSecurityConfigurer configure");

        http.authorizeRequests(
                authorize -> authorize.anyRequest()
                        .permitAll()
//                .authenticated()
        )
//                .formLogin(withDefaults()) // 启用Spring Security自带的登陆页面
//                .httpBasic(withDefaults());
//                .cors()
//                .and()
                .csrf().disable();
    }
}
