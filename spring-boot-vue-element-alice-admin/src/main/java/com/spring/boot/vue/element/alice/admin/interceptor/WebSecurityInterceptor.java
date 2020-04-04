package com.spring.boot.vue.element.alice.admin.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.spring.boot.vue.element.alice.admin.constant.ResponseConstant;
import com.spring.boot.vue.element.alice.admin.result.JSONResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * <p>Description: Web SecurityInterceptor</p>
 *
 * @author rock.jiang
 * Date 2019/11/28 17:18
 */
@Component
public class WebSecurityInterceptor extends HandlerInterceptorAdapter {
    private static final Logger logger = LoggerFactory.getLogger(WebSecurityInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.debug("WebSecurityInterceptor 验证 开始 当前线程ID: {} 线程名称: {}", Thread.currentThread().getId(), Thread.currentThread().getName());

        // 1. 验证token
        String token = request.getHeader("token");
        // 验证token是否存在
        if (StringUtils.isEmpty(token)) {
            JSONResult jsonResult = new JSONResult(ResponseConstant.STATUS_UNAUTHORIZED_CODE, ResponseConstant.STATUS_UNAUTHORIZED_MESSAGE);

            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json;charset=UTF8");
            PrintWriter writer = response.getWriter();
            writer.write(JSONObject.toJSONString(jsonResult));
            writer.close();

            logger.debug("WebSecurityInterceptor 验证 token不存在");
            return false;
        }

        // 验证token中内容是否正确

        // 2. 验证签名

        logger.debug("WebSecurityInterceptor 验证 结束");
        return true;
    }

}
