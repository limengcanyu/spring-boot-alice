package com.spring.boot.security.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/authenticate")
@RestController
public class AuthenticateController {

    /**
     * localhost:8080/authenticate/login?username=rock&password=1234567890
     *
     * @return
     */
    @RequestMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password){

        return "login success";
    }

    /**
     * localhost:8080/authenticate/register?username=rock&password=1234567890
     *
     * @return
     */
    @RequestMapping("/register")
    public String register(@RequestParam String username, @RequestParam String password){

        return "register success";
    }

    /**
     * localhost:8080/authenticate/logout?username=rock
     *
     * @return
     */
    @RequestMapping("/logout")
    public String logout(@RequestParam String username){

        return "logout success";
    }
}
