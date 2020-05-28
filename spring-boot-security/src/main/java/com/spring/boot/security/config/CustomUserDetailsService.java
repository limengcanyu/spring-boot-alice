package com.spring.boot.security.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * <p>Description: UserDetailsService 根据登录用户名从数据库中获取用户信息</p>
 *
 * @author rock
 * date 2019/06/23
 */
@Slf4j
public class CustomUserDetailsService implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.debug("====== loadUserByUsername");
        return User.withUsername("rock").password("1234567").roles("ADMIN").build();
    }
}
