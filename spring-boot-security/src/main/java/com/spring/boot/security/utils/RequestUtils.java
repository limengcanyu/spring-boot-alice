package com.spring.boot.security.utils;

import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * <p>Description: HttpServletRequest Utils </p>
 *
 * @author rock.jiang
 * Date 2020/01/03 18:08
 */
public class RequestUtils {
    public static final String TOKEN_NAME = "Authorization";

    public static String getToken(HttpServletRequest request) {
        String token = request.getHeader(TOKEN_NAME);
        if (StringUtils.isEmpty(token)) {
            token = request.getParameter(TOKEN_NAME);
        }

        return token;
    }

    public static SortedMap<String, String> getSortedParams(HttpServletRequest request) {
        SortedMap<String, String> paramMap = new TreeMap<>();

        Map<String, String[]> parameterMap = request.getParameterMap();
        Set<Map.Entry<String, String[]>> entrySet = parameterMap.entrySet();
        if (!CollectionUtils.isEmpty(entrySet)) {
            for (Map.Entry<String, String[]> entry : entrySet) {
                String parameterName = entry.getKey();
                String[] parameterValues = entry.getValue();
                paramMap.put(parameterName, String.join("", parameterValues));
            }
        }

        return paramMap;
    }
}
