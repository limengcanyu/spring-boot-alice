package com.spring.boot.security.service;

import org.springframework.security.core.GrantedAuthority;

import java.util.Set;

/**
 * <p>Description: 用户角色 服务类</p>
 *
 * @author Rock Jiang
 * @version 1.0
 * @date 2018/4/4 0004
 */
public interface UserAuthorityService {
    /**
     * 根据userId查询用户角色集合
     *
     * @param userId
     * @return
     */
    Set<GrantedAuthority> selectAuthoritySetByUserId(Integer userId);
}
