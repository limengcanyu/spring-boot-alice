package com.spring.boot.vue.element.alice.admin.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>Description: Weixin SecurityInterceptor</p>
 *
 * @author rock.jiang
 * Date 2019/11/28 17:18
 */
@Component
public class WeixinSecurityInterceptor extends HandlerInterceptorAdapter {
    private static final Logger logger = LoggerFactory.getLogger(WeixinSecurityInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.debug("WeixinSecurityInterceptor 验证 开始 当前线程ID: {} 线程名称: {}", Thread.currentThread().getId(), Thread.currentThread().getName());

        logger.debug("WeixinSecurityInterceptor 验证 结束");
        return true;
    }
}
