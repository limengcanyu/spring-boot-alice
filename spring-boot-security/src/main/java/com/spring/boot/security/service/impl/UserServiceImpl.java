package com.spring.boot.security.service.impl;


import com.spring.boot.security.entity.User;
import com.spring.boot.security.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * <p>Description: </p>
 *
 * @author rock
 * date 2019/06/23
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {
    @Override
    public User loadUserByUsername(String username) {
        log.debug("====== loadUserByUsername");
        return new User("rock", "1234567890");
    }
}
