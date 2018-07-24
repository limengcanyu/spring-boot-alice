package com.spring.boot.security.service.impl;

import com.spring.boot.security.service.UserAuthorityService;
import com.spring.boot.security.service.UserInfoService;
import com.spring.boot.security.user.UserInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * <p>Description: 用户信息 服务实现类</p>
 *
 * @author Rock Jiang
 * @version 1.0
 * @date 2018/4/4 0004
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {
    private static final Logger logger = LoggerFactory.getLogger(UserInfoServiceImpl.class);

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private UserAuthorityService userAuthorityService;

    @Override
    public UserInfo selectUserInfo(String username) {
        logger.debug("根据username查询用户信息 username: {}", username);

        UserInfo userInfo = new UserInfo("user", passwordEncoder.encode("123456"), userAuthorityService.selectAuthoritySetByUserId(123));

        logger.debug("根据username查询用户信息 userInfo: {}", userInfo);
        return userInfo;
    }
}
