package com.veu.element.alice.admin.utils;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

/**
 * <p>Description: Signature Utils </p>
 *
 * @author rock.jiang
 * Date 2019/12/25 17:47
 */
public class SignatureUtils {
    private static final Logger logger = LoggerFactory.getLogger(SignatureUtils.class);

    @SuppressWarnings("rawtypes")
    public static String getMd5SignString(long timestamp, String token, String signStr, SortedMap<String, Object> paramMap) {
        if (CollectionUtils.isEmpty(paramMap)) {
            return null;
        }

        List<String> paramList = new ArrayList<>();

        Set<Map.Entry<String, Object>> entrySet = paramMap.entrySet();
        for (Map.Entry<String, Object> entry : entrySet) {
            logger.debug("entry: {}", JSONObject.toJSONString(entry));
            String key = entry.getKey();
            Object value = entry.getValue();

            if (value instanceof String || value instanceof Number) {
                paramList.add(key + value);
            } else if (value instanceof List) {
                paramList.add(key + String.join(",", (List) value));
            }
        }

        signStr = timestamp + token + String.join("", paramList);
        logger.debug("signStr： {}", signStr);

        String md5Str = DigestUtils.md5DigestAsHex(signStr.getBytes(StandardCharsets.UTF_8));
        logger.debug("md5Str： {}", md5Str);

        return md5Str;
    }
}
