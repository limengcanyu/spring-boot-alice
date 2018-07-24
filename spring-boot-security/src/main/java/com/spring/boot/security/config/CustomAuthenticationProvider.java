package com.spring.boot.security.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.ObjectUtils;

/**
 * <p>Description: 自定义AuthenticationProvider</p>
 *
 * AuthenticationManager 是一个用来处理认证（Authentication）请求的接口。
 *
 * 在 Spring Security 中，AuthenticationManager 的默认实现是 ProviderManager，而且它不直接自己处理认证请求，
 * 而是委托给其所配置的 AuthenticationProvider 列表，然后会依次使用每一个 AuthenticationProvider 进行认证，
 * 如果有一个 AuthenticationProvider 认证后的结果不为 null，则表示该 AuthenticationProvider 已经认证成功，
 * 之后的 AuthenticationProvider 将不再继续认证。然后直接以该 AuthenticationProvider 的认证结果作为 ProviderManager 的认证结果。
 *
 * 如果所有的 AuthenticationProvider 的认证结果都为 null，则表示认证失败，将抛出一个 ProviderNotFoundException。
 *
 * 校验认证请求最常用的方法是根据请求的用户名加载对应的 UserDetails，然后比对 UserDetails 的密码与认证请求的密码是否一致，一致则表示认证通过。
 *
 * Spring Security 内部的 DaoAuthenticationProvider 就是使用的这种方式。其内部使用 UserDetailsService 来负责加载 UserDetails。
 *
 * 在认证成功以后会使用加载的 UserDetails 来封装要返回的 Authentication 对象，加载的 UserDetails 对象是包含用户权限等信息的。
 *
 * 认证成功返回的 Authentication 对象将会保存在当前的 SecurityContext 中。
 *
 * @author Rock Jiang
 * @version 1.0
 * @date 2018/4/11 0011
 */
public class CustomAuthenticationProvider implements AuthenticationProvider {
    private static final Logger logger = LoggerFactory.getLogger(CustomAuthenticationProvider.class);

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        logger.debug("自定义AuthenticationProvider 登录认证开始");

        // Determine username
        String username = (authentication.getPrincipal() == null) ? "NONE_PROVIDED" : authentication.getName();
        String password = (String) authentication.getCredentials();
        logger.debug("当前登录用户 username: {} password: {}", username, password);

        UserDetails user = userDetailsService.loadUserByUsername(username);
        logger.debug("数据库中用户 username: {} password: {}", user.getUsername(), user.getPassword());

        if (ObjectUtils.isEmpty(user)) {
            logger.debug("当前用户信息不存在");
            throw new UsernameNotFoundException("username不存在");
        }

        if (!passwordEncoder.matches(password, user.getPassword())){
            logger.debug("用户密码不正确");
            throw new BadCredentialsException("用户密码不正确");
        }

        logger.debug("自定义AuthenticationProvider 登录认证成功");
        return new UsernamePasswordAuthenticationToken(username, password, user.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}
