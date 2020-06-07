package com.spring.boot.security.controller;

import com.spring.boot.security.entity.JwtUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping("/security/auth")
@RestController
public class AuthenticateController {

    @Autowired
    private UserDetailsService userDetailsService;

    /**
     * localhost:8080/auth/login?username=rock&password=1234567890
     *
     * @return
     */
    @RequestMapping("/login")
    public String login(@RequestBody JwtUser jwtUser) throws Exception {
        log.debug("====== login jwtUser: {}", jwtUser);

        // SecurityContextHolder 中设置 SecurityContext 表示认证成功
//        SecurityContext context = SecurityContextHolder.createEmptyContext();
//        Collection<GrantedAuthority> authorities = new ArrayList<>();
//        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
//        Authentication authentication = new UsernamePasswordAuthenticationToken(jwtUser.getUsername(), jwtUser.getPassword(), authorities);
//        context.setAuthentication(authentication);
//
//        SecurityContextHolder.setContext(context);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        log.debug("====== authentication: {}", authentication);

//        // 登录成功之后根据 UserDetails 生成 token 返回
//        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        return "login return token";
    }

    /**
     * localhost:8080/auth/register?username=rock&password=1234567890
     *
     * @return
     */
    @RequestMapping("/register")
    public String register(@RequestParam String username, @RequestParam String password){
        return "register success";
    }

    /**
     * localhost:8080/auth/logout?username=rock
     *
     * @return
     */
    @RequestMapping("/logout")
    public String logout(@RequestParam String username){
        log.debug("====== logout");

        SecurityContextHolder.clearContext();

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        log.debug("====== authentication: {}", authentication);

        return "logout success";
    }
}
