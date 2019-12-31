package com.veu.element.alice.admin.utils;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.DigestUtils;
import org.springframework.util.ObjectUtils;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;

/**
 * <p>Description: Signature Utils </p>
 *
 * @author rock.jiang
 * Date 2019/12/25 17:47
 */
public class SignatureUtils {
    private static final Logger logger = LoggerFactory.getLogger(SignatureUtils.class);

    /**
     * 验证签名
     * 根据前端请求Header中的时间戳、token、请求参数，在后端再生成签名字符串，然后比较前端传入的签名字符串和后端生成的签名字符串，
     * 若两者一致，则表示请求参数未被篡改。
     *
     * @param timestamp
     * @param token
     * @param signStringOfRequest
     * @param paramMap
     * @return
     */
    public static boolean verifySignature(long timestamp, String token, String signStringOfRequest, SortedMap<String, Object> paramMap) {
        String signString = generateSignString(timestamp, token, paramMap);

        return ObjectUtils.nullSafeEquals(signStringOfRequest, signString);
    }

    public static String generateSignString(long timestamp, String token, SortedMap<String, Object> paramMap) {
        String pramsString = getSortedParamsString(paramMap);
        logger.debug("参数字符串： {}", pramsString);

        String signString = token + timestamp + pramsString;
        logger.debug("签名字符串： {}", signString);

        String md5String = DigestUtils.md5DigestAsHex(signString.getBytes(StandardCharsets.UTF_8));
        logger.debug("md5字符串： {}", md5String);

        return md5String;
    }

    /**
     * 获取有序参数字符串
     *
     * @param paramMap
     * @return
     */
    public static String getSortedParamsString(SortedMap<String, Object> paramMap) {
        return String.join("", getParamsStringList(paramMap)).replaceAll(",", "");
    }

    public static List<String> getParamsStringList(Object params) {
        logger.debug("获取参数字符串数组 开始 ======================================================================");
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
                    paramsList.add(String.join("", getParamsStringList(element)));
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
                    paramsList.addAll(getParamsStringList(value));
                }
            });
        }

        paramsList.sort(String::compareTo);

        logger.debug("函数返回结果: {}", JSONObject.toJSONString(paramsList));
        logger.debug("获取参数字符串数组 结束 ======================================================================\n");
        return paramsList;
    }
}

