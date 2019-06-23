package com.spring.boot.security.service;

import com.spring.boot.security.entity.User;

/**
 * <p>Description: </p>
 *
 * @author rock
 * date 2019/06/23
 */
public interface UserService {
    User loadUserByUsername(String username);
}
