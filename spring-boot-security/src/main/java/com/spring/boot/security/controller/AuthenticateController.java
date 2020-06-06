package com.spring.boot.security.controller;

import com.spring.boot.security.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping("/auth")
@RestController
public class AuthenticateController {

    /**
     * localhost:8080/auth/login?username=rock&password=1234567890
     *
     * @return
     */
    @RequestMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password){
        log.debug("login ===========================================================");
        return "login success";
    }

    /**
     * localhost:8080/auth/login?username=rock&password=1234567890
     *
     * @return
     */
    @RequestMapping("/login2")
    public String login2(@RequestBody User user){
        log.debug("login ===========================================================");
        return "login success";
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

        return "logout success";
    }
}
