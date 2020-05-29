package com.spring.boot.security.controller;

import com.spring.boot.security.entity.User;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Collections;

/**
 * <p>Description: </p>
 *
 * @author rock.jxf
 * @date 2020/5/29 22:03
 */
@RestController
public class UserController {
    /**
     * localhost:8080/vue-element-admin/user/login?username=admin&password=123
     *
     * @return
     */
    @RequestMapping("/vue-element-admin/user/login")
    public String login(@RequestParam String username,  @RequestParam String password) {
        return "admin" + "-token";
    }

    @GetMapping("/vue-element-admin/user/info")
    public User info(@RequestParam String token) {
        User user = new User();
        user.setName("Super Admin");
        user.setAvatar("https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        user.setIntroduction("I am a super administrator");
        user.setRoles(Collections.singletonList("admin"));
        user.setRoutes(Arrays.asList("/permission", "page", "directive", "role", "/icon", "index", "/components", "tinymce"));
        return user;
    }
}
