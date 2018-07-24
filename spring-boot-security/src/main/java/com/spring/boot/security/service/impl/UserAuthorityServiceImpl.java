package com.spring.boot.security.service.impl;

import com.spring.boot.security.service.UserAuthorityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * <p>Description: 用户角色 服务实现类</p>
 *
 * @author Rock Jiang
 * @version 1.0
 * @date 2018/4/4 0004
 */
@Service
public class UserAuthorityServiceImpl implements UserAuthorityService {
    private static final Logger logger = LoggerFactory.getLogger(UserAuthorityServiceImpl.class);

    @Override
    public Set<GrantedAuthority> selectAuthoritySetByUserId(Integer userId) {
        logger.debug("根据userId查询用户角色集合 userId: {}", userId);

        Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER")); //此处权限需加前缀: ROLE_，才能和WebSecurityConfig中的权限匹配上
        authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));

        logger.debug("根据userId查询用户角色集合 authorities: {}", authorities);
        return authorities;
    }
}
