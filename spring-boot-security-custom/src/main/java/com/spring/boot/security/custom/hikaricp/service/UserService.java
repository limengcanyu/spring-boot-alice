package com.spring.boot.security.custom.hikaricp.service;

import com.spring.boot.security.custom.dto.User;

/**
 * <p>Description: 用户 服务类</p>
 *
 * @author Rock Jiang
 * @version 1.0
 * @date 2018/4/18 0018
 */
public interface UserService {

    /**
     * 根据userId查询用户信息
     *
     * @param username
     * @return
     */
    User selectInfo(String username);
}
