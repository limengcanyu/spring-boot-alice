package com.spring.boot.security.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

/**
 * <p>Description: Hello Controller</p>
 *
 * @author rock
 * date 2019/06/23
 */
@Slf4j
@RestController
public class HelloController {
    /**
     * localhost:8080
     *
     * @return
     */
    @RequestMapping("/security")
    public String home() {
        log.debug("====== home");

        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        String username = authentication.getName();
        Object principal = authentication.getPrincipal();
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        log.debug("username: {} principal: {} authorities: {}", username, principal, authorities);

        return "home";
    }

    /**
     * localhost:8080/security/hello
     *
     * @return
     */
    @RequestMapping("/security/hello")
    public String hello() {
        log.debug("====== hello");

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        log.debug("====== authentication: {}", authentication);

        String username = authentication.getName();
        Object principal = authentication.getPrincipal();
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        log.debug("====== username: {} principal: {} authorities: {}", username, principal, authorities);

        return "hello";
    }
}
