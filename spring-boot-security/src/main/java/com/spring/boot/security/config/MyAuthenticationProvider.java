package com.spring.boot.security.config;

import com.spring.boot.security.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * <p>Description: AuthenticationProvider 负责认证输入用户信息与数据库中用户信息</p>
 *
 * @author rock
 * date 2019/06/23
 */
@Slf4j
public class MyAuthenticationProvider extends DaoAuthenticationProvider {
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
        log.debug("====== additionalAuthenticationChecks");

        if (authentication.getCredentials() == null) {
            logger.debug("认证失败: no credentials provided");

            throw new BadCredentialsException(messages.getMessage("1", "Bad credentials"));
        }

        String presentedPassword = userService.loadUserByUsername(authentication.getName()).getPassword();

        if (!passwordEncoder.matches(presentedPassword, passwordEncoder.encode(userDetails.getPassword()))) {
            logger.debug("认证失败: password does not match stored value");

            throw new BadCredentialsException(messages.getMessage("1", "Bad credentials"));
        }
    }
}
