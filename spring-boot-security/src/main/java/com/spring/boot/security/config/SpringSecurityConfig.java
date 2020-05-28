package com.spring.boot.security.config;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationEventPublisher;
import org.springframework.security.authentication.DefaultAuthenticationEventPublisher;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * <p>Description: SpringSecurity Config</p>
 *
 * @author rock
 * date 2019/06/23
 */
@Configuration
public class SpringSecurityConfig {
    /**
     * DaoAuthenticationProvider is an AuthenticationProvider implementation that leverages a UserDetailsService and PasswordEncoder
     * to authenticate a username and password.
     *
     * @return
     */
    @Bean
    public CustomDaoAuthenticationProvider authenticationProvider() {
        CustomDaoAuthenticationProvider authenticationProvider = new CustomDaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(customUserDetailsService());
        return authenticationProvider;
    }

    /**
     * UserDetailsService is used by DaoAuthenticationProvider for retrieving a username, password,
     * and other attributes for authenticating with a username and password.
     *
     * @return
     */
    @Bean
    public CustomUserDetailsService customUserDetailsService() {
        return new CustomUserDetailsService();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CustomWebSecurityConfigurer customWebSecurityConfigurer(){
        return new CustomWebSecurityConfigurer();
    }
}
