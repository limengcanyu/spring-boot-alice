package com.spring.boot.security.token.cluster.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <p>Description: </p>
 *
 * @author Rock Jiang
 * @version 1.0
 * @date 2018/4/19 0019
 */
@RestController
public class HelloController {
    private static final Logger logger = LoggerFactory.getLogger(HelloController.class);

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @RequestMapping("/hello/service")
    public String printService(HttpSession httpSession) {
        logger.debug("---------- HelloController printService处理 开始 ----------");

        logger.debug("---------- HelloController printService处理 完成 ----------");
        return "cluster1 service";
    }
}
