package com.spring.boot.security.utils;

import com.spring.boot.security.entity.Result;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;

/**
 * <p>Description: HttpServletResponse Utils </p>
 *
 * @author rock.jiang
 * Date 2020/01/03 17:17
 */
public class ResponseUtils {
    /**
     * 写失败响应
     *
     * @param response
     * @param result
     * @throws IOException
     */
    public static void writeFailedResponse(HttpServletResponse response, Result result) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=UTF8");
        PrintWriter writer = response.getWriter();
        writer.write(Objects.requireNonNull(JacksonUtils.writeValueAsString(result)));
        writer.close();
    }
}
