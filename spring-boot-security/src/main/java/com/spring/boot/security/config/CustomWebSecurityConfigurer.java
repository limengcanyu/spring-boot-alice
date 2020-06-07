package com.spring.boot.security.config;

import com.spring.boot.security.filter.JwtTokenFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Slf4j
@Configuration
public class CustomWebSecurityConfigurer extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtTokenFilter jwtTokenFilter;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());

//        //校验用户
//        auth.userDetailsService(jwtUserDetailsService()).passwordEncoder(new PasswordEncoder() {
//            // 密码加密
//            @Override
//            public String encode(CharSequence charSequence) {
//                System.out.println(charSequence.toString());
//                return DigestUtils.md5DigestAsHex(charSequence.toString().getBytes());
//            }
//
//            // 密码匹配
//            @Override
//            public boolean matches(CharSequence charSequence, String s) {
//                String encode = DigestUtils.md5DigestAsHex(charSequence.toString().getBytes());
//                boolean res = s.equals(encode);
//                return res;
//            }
//        });
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        log.debug("====== CustomWebSecurityConfigurer configure");

        http.csrf().disable().cors().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests().antMatchers("/security/auth/login", "/security/auth/register").permitAll().and()
                .authorizeRequests().anyRequest().authenticated().and()
                .headers().cacheControl();

        // 添加自定义token过滤器校验，在 UsernamePasswordAuthenticationFilter 过滤器执行之前执行
        http.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);

//        http
//                // 由于使用的是JWT，我们这里不需要csrf
//                .csrf().disable()
//                // 基于token，所以不需要session
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
//                .authorizeRequests()
//                //.antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
//                // 允许对于网站静态资源的无授权访问
//                .antMatchers(
//                        HttpMethod.GET,
//                        "/",
//                        "/*.html",
//                        "/favicon.ico",
//                        "/**/*.html",
//                        "/**/*.css",
//                        "/**/*.js"
//                ).permitAll()
//                // 对于获取token的rest api要允许匿名访问
//                .antMatchers("/auth/**").permitAll()
//                // 除上面外的所有请求全部需要鉴权认证
//                .anyRequest().authenticated();
//
//        // 禁用缓存
//        http.headers().cacheControl();

    }
}
