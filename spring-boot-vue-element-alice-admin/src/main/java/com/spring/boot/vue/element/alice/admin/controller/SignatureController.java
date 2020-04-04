package com.spring.boot.vue.element.alice.admin.controller;

import com.spring.boot.vue.element.alice.admin.utils.SignatureUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;

/**
 * <p>Description: </p>
 *
 * @author rock.jiang
 * Date 2019/12/25 17:21
 */
@RequestMapping("/signature")
@RestController
public class SignatureController {
    private static final Logger logger = LoggerFactory.getLogger(SignatureController.class);

    @RequestMapping("/sample")
    public Map<String, Object> sample(HttpServletRequest request, HttpServletResponse response, @RequestBody SortedMap<String, Object> paramMap) {
        // 从请求Header中获取签名用的时间戳、请求中签名字符串、token
        int level = request.getIntHeader("level");
        logger.debug("加密级别: {}", level);

        String token = request.getHeader("token");
        long timestamp = Long.parseLong(request.getHeader("timestamp"));
        String signStringOfRequest = request.getHeader("signStr");
        logger.debug("token: {} timestamp: {} signStr: {}", token, timestamp, signStringOfRequest);

        Map<String, Object> retMap = new HashMap<>();

        if (SignatureUtils.verifySignature(timestamp, token, signStringOfRequest, paramMap)){
            logger.debug("签名验证成功 ==================================================================");
            retMap.put("code", 0);
            retMap.put("msg", "签名验证成功!");
        } else {
            logger.debug("签名验证失败 ==================================================================");
            retMap.put("code", 1);
            retMap.put("msg", "签名验证失败!");
        }

        return retMap;
    }

}
