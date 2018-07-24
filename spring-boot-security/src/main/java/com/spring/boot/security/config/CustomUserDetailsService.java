package com.spring.boot.security.config;

import com.spring.boot.security.service.UserInfoService;
import com.spring.boot.security.user.UserInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.util.ObjectUtils;

import java.util.Optional;

/**
 * <p>Description: 自定义UserDetailsService</p>
 *
 * @author Rock Jiang
 * @version 1.0
 * @date 2018/4/4 0004
 */
public class CustomUserDetailsService implements UserDetailsService {
    private static final Logger logger = LoggerFactory.getLogger(CustomUserDetailsService.class);

    @Autowired
    private UserInfoService userInfoService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.debug("CustomUserDetailsService 根据username载入用户信息 username: {}", username);

        UserInfo userInfo = userInfoService.selectUserInfo(username);

        if (ObjectUtils.isEmpty(userInfo)) {
            throw new UsernameNotFoundException("当前用户信息不存在");
        }

        logger.debug("CustomUserDetailsService 根据username载入用户信息 userInfo: {}", userInfo);
        return userInfo;
    }
}
