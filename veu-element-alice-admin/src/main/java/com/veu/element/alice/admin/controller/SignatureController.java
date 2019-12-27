package com.veu.element.alice.admin.controller;

import com.veu.element.alice.admin.utils.SignatureUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ObjectUtils;
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
    public Map<String, Object> sample(HttpServletRequest request, HttpServletResponse response, @RequestBody Map<String, Object> paramMap) {
        int level = request.getIntHeader("level");
        long timestamp = Long.parseLong(request.getHeader("timestamp"));
        String signStr = request.getHeader("signStr");
        logger.debug("token: token timestamp: {} signStr: {}", timestamp, signStr);

        String md5SignStr = SignatureUtils.getMd5SignString(timestamp, "token", paramMap);

        Map<String, Object> retMap = new HashMap<>();

        if (ObjectUtils.nullSafeEquals(signStr, md5SignStr)){
            logger.debug("签名验证成功 ==================================================================");

//            response.setStatus(1000); // 设置响应状态码，非HTTP 200 则HTTP返回错误
            retMap.put("code", 1000);
            retMap.put("msg", "签名验证成功!");
        } else {
            logger.debug("签名验证失败 ==================================================================");

            retMap.put("code", 1);
            retMap.put("msg", "签名验证失败!");
        }

        return retMap;
    }

}
