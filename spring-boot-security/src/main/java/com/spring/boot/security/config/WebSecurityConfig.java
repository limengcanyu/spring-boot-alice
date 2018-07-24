package com.spring.boot.security.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.RememberMeAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.rememberme.RememberMeAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices;
import org.springframework.session.security.web.authentication.SpringSessionRememberMeServices;


/**
 * <p>Description: Spring Security 配置</p>
 *
 * @author Rock Jiang
 * @version 1.0
 * @date 2018/4/4 0004
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private static final Logger logger = LoggerFactory.getLogger(WebSecurityConfig.class);

//    @Autowired
//    private RememberMeServices rememberMeServices;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        logger.debug("HttpSecurity配置");

        http.csrf().disable(); //关闭CSRF

        http
                .authorizeRequests()
                .antMatchers("/login", "/logout", "/home").permitAll()
                .antMatchers("/customLogin", "/customLogout").permitAll() //登录请求不验证，登出url不能使用"/logout"，否则会使用认证机制
                .antMatchers("/time/*").hasRole("USER") //匹配"/time/**"的请求需要有ROLE_USER角色才能访问，此处会自动加上前缀"ROLE_"
                .antMatchers("/db/**").access("hasRole('ADMIN') and hasRole('DBA')") //配置双重访问角色
                .anyRequest().authenticated() //其余请求必须认证通过才能访问
                .and()
                .logout()
                .invalidateHttpSession(true) //登出后使当前Session失效
                .clearAuthentication(true) //登出后清除认证信息
                .logoutSuccessUrl("/home") //登出成功之后跳转的url
        ;

//        // Spring Security Remember-Me Support
//        http.rememberMe().rememberMeServices(rememberMeServices);

    }

    /**
     * 自定义认证管理器：处理用户登录认证
     *
     * @return
     */
    @Bean
    public CustomAuthenticationProvider authenticationProvider() {
        logger.debug("自定义AuthenticationProvider配置");
        return new CustomAuthenticationProvider();
    }

    /**
     * 登录认证通过后，获取用户信息
     *
     * @return
     */
    @Bean
    public CustomUserDetailsService userDetailsService() {
        logger.debug("自定义UserDetailsService配置");
        return new CustomUserDetailsService();
    }

    /**
     * 密码编码
     * 此类的encode方法对登录密码进行编码，然后调用matches方法使用编码密码与数据库中的用户密码匹配
     *
     * @return
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


//    /**
//     * Spring Security Remember-Me Support
//     *
//     * @return
//     */
//    @Bean
//    public RememberMeServices rememberMeServices() {
//        logger.debug("SpringSessionRememberMeServices配置");
//
//        // A RememberMeServices implementation that uses Spring Session backed HttpSession to provide remember-me service capabilities.
//        SpringSessionRememberMeServices rememberMeServices = new SpringSessionRememberMeServices();
//
//        // optionally customize
//        rememberMeServices.setAlwaysRemember(true);
//        return rememberMeServices;
//    }
//
//    @Bean
//    public TokenBasedRememberMeServices tokenBasedRememberMeServices() {
//        logger.debug("TokenBasedRememberMeServices配置");
//
//        // Identifies previously remembered users by a Base-64 encoded cookie.
//        // 通过 Base-64 编码的 cookie 标识以前记住的用户
//        TokenBasedRememberMeServices rememberMeServices = new TokenBasedRememberMeServices("springRocks", springDataUserDetailsService());
//
//        // optionally customize
//        rememberMeServices.setAlwaysRemember(true);
//        return rememberMeServices;
//    }
//
//    /**
//     * Token Based Remember Me Services
//     * <p>
//     * Detects if there is no Authentication object in the SecurityContext,
//     * and populates the context with a remember-me authentication token if a RememberMeServices implementation so requests.
//     *
//     * @return
//     */
//    @Bean
//    public RememberMeAuthenticationFilter rememberMeFilter() {
//        return new RememberMeAuthenticationFilter(authenticationManager(), tokenBasedRememberMeServices());
//    }
//
//    /**
//     * Token Based Remember Me Services
//     * <p>
//     * validates RememberMeAuthenticationToken
//     * To be successfully validated, the RememberMeAuthenticationToken.getKeyHash() must match this class' getKey().
//     *
//     * @return
//     */
//    @Bean
//    public RememberMeAuthenticationProvider rememberMeAuthenticationProvider() {
//        return new RememberMeAuthenticationProvider("springRocks");
//    }
}
