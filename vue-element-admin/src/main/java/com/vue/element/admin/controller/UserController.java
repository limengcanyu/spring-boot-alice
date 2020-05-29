package com.vue.element.admin.controller;

import com.vue.element.admin.entity.Result;
import com.vue.element.admin.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Collections;

/**
 * <p>Description: </p>
 *
 * @author rock.jxf
 * @date 2020/5/29 22:03
 */
@Slf4j
@RestController
public class UserController {

    @PostMapping("/vue-element-admin/user/login")
    public Result login(@RequestBody User user) {
        log.debug("====== login user: {}", user);

        System.out.println("====== login user: " + user);

        return new Result(20000, null, user.getUsername() + "-token");
    }

    @GetMapping("/vue-element-admin/user/info")
    public Result info(@RequestParam String token) {
        log.debug("====== info token: {}", token);

        System.out.println("====== info token: " + token);

        User user = new User();
        user.setName("Super Admin");
        user.setAvatar("https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        user.setIntroduction("I am a super administrator");
        user.setRoles(Collections.singletonList("admin"));
        user.setRoutes(Arrays.asList("/permission", "page", "directive", "role", "/icon", "index", "/components", "tinymce"));
        return new Result(20000, null, user);
    }

    @PostMapping("/vue-element-admin/user/logout")
    public Result logout() {
        log.debug("====== logout");

        System.out.println("====== logout");

        return new Result(20000, null, null);
    }

}
