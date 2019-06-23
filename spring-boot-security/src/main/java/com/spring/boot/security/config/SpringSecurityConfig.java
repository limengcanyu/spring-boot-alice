package com.spring.boot.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * <p>Description: SpringSecurity Config</p>
 *
 * @author rock
 * date 2019/06/23
 */
@Configuration
public class SpringSecurityConfig {
    @Bean
    public MyAuthenticationProvider authenticationProvider() {
        MyAuthenticationProvider authenticationProvider =  new MyAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        return authenticationProvider;
    }

    @Bean
    public MyUserDetailsService userDetailsService() {
        return new MyUserDetailsService();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
