package com.veu.element.alice.admin.utils;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>Description: Signature Utils </p>
 *
 * @author rock.jiang
 * Date 2019/12/25 17:47
 */
public class SignatureUtils {
    private static final Logger logger = LoggerFactory.getLogger(SignatureUtils.class);

    public static String getMd5SignString(long timestamp, String token, Map<String, Object> paramMap) {
        String pramsString = String.join("", getPramsStringList(paramMap)).replaceAll(",", "");
        logger.debug("参数字符串： {}", pramsString);

        String signStr = timestamp + token + pramsString;
        logger.debug("签名字符串： {}", signStr);

        String md5Str = DigestUtils.md5DigestAsHex(signStr.getBytes(StandardCharsets.UTF_8));
        logger.debug("md5摘要字符串： {}", md5Str);

        return md5Str;
    }

    public static List<String> getPramsStringList(Object params) {
        logger.debug("获取参数字符串数组2 开始 ======================================================================");
        logger.debug("参数: {}", JSONObject.toJSONString(params));

        List<String> paramsList = new ArrayList<>();

        if (params instanceof List) {
            logger.debug("参数为数组 ======================================================================");
            List paramsArray = (List) params;
            for (Object element : paramsArray) {
                if (element instanceof String || element instanceof Number) {
                    logger.debug("元素为字符串或者数字 元素: " + JSONObject.toJSONString(element));
                    paramsList.add(element.toString());
                } else {
                    logger.debug("元素非字符串或者数字 元素: " + JSONObject.toJSONString(element));
                    paramsList.add(String.join("", getPramsStringList(element)));
                }
            }
        } else {
            logger.debug("参数非数组 ======================================================================");

            Map<String, Object> paramsMap = (Map) params;
            paramsMap.forEach((field, value) -> {
                if (value instanceof String || value instanceof Number) {
                    logger.debug("字段为字符串或者数字 名称: {} 值: {}", field, value);
                    paramsList.add(field + value);
                } else {
                    logger.debug("字段非字符串或者数字 名称: {} 值: {}", field, value);
                    paramsList.addAll(getPramsStringList(value));
                }
            });
        }

        paramsList.sort(String::compareTo);

        logger.debug("函数返回结果: {}", JSONObject.toJSONString(paramsList));
        logger.debug("获取参数字符串数组2 结束 ======================================================================");
        return paramsList;
    }
}

