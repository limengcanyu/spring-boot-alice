package com.spring.boot.security.custom.hikaricp.service.impl;

import com.spring.boot.security.custom.dto.User;
import com.spring.boot.security.custom.hikaricp.service.UserService;
import org.springframework.stereotype.Service;

/**
 * <p>Description: 用户 服务实现类</p>
 *
 * @author Rock Jiang
 * @version 1.0
 * @date 2018/4/18 0018
 */
@Service
public class UserServiceImpl implements UserService {
    @Override
    public User selectInfo(String username) {
        User user = new User();
        user.setUsername("rock");
        user.setPassword("123456");
        return user;
    }
}
