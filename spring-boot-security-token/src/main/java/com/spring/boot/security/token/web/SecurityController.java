package com.spring.boot.security.token.web;

import com.spring.boot.security.token.dto.User;
import com.spring.boot.security.token.result.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.UUID;

/**
 * <p>Description: 安全 Controller 负责登录认证，登出注销</p>
 *
 * @author Rock Jiang
 * @version 1.0
 * @date 2018/4/18 0018
 */
@RestController
public class SecurityController {
    private static final Logger logger = LoggerFactory.getLogger(SecurityController.class);

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 1.验证用户登录信息
     * 2.登录成功后将用户信息放到Redis共享session中
     *
     * @param username
     * @param password
     * @return
     */
    @RequestMapping("/login")
    public Result login(@RequestParam String username, @RequestParam String password, HttpServletResponse response) {
        logger.debug("---------- 安全Controller 登录处理 开始 ----------");
        logger.debug("username: {} password: {}", username, password);

        User user = new User();
        logger.debug("数据库用户信息 user: {}", user);
        if (ObjectUtils.isEmpty(user)) {
            logger.debug("用户不存在，请先注册");
            return new Result(10001, "用户不存在，请先注册");
        }

//        if (!username.equals(user.getUsername()) || !password.equals(user.getPassword())) {
//            logger.debug("用户密码错误");
//            return new Result(10002, "用户密码错误");
//        }

        // 用户账户状态检查：1-正常；2-禁用

        // 验证用户信息成功后，生成Token
        String token = UUID.randomUUID().toString().replace("-", "");
        logger.debug("token: {}", token);

        stringRedisTemplate.opsForValue().set("token-" + username, token);

        logger.debug("---------- 安全Controller 登录处理 完成 ----------");
        return new Result();
    }

    /**
     * 1.清除用户token信息
     * 2.清除Redis中用户信息
     *
     * @return
     */
    @RequestMapping("/logout")
    public Result logout(@RequestParam String username, HttpSession httpSession) {
        logger.debug("---------- 安全Controller 登出处理 开始 ----------");
        httpSession.removeAttribute(username);
        logger.debug("---------- 安全Controller 登出处理 完成 ----------");
        return new Result();
    }
}
