package com.spring.boot.security.service;

import com.spring.boot.security.user.UserInfo;

/**
 * <p>Description: 用户信息 服务类</p>
 *
 * @author Rock Jiang
 * @version 1.0
 * @date 2018/4/4 0004
 */
public interface UserInfoService {
    /**
     * 根据username查询用户信息
     *
     * @param username
     * @return
     */
    UserInfo selectUserInfo(String username);
}
