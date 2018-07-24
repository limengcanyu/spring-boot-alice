package com.spring.boot.security.custom.cluster.intercepter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <p>Description: 安全拦截器</p>
 *
 * @author Rock Jiang
 * @version 1.0
 * @date 2018/4/18 0018
 */
public class SecurityInterceptor extends HandlerInterceptorAdapter {
    private static final Logger logger = LoggerFactory.getLogger(SecurityInterceptor.class);

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    public SecurityInterceptor() {
        super();
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.debug("---------- 安全拦截器 预处理 开始 ----------");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");

        HttpSession httpSession = request.getSession();
        logger.debug("获取缓存中Session信息 SessionId: {} getCreationTime: {} getLastAccessedTime: {} getMaxInactiveInterval: {} seconds", httpSession.getId(), simpleDateFormat.format(new Date(httpSession.getCreationTime())), simpleDateFormat.format(new Date(httpSession.getLastAccessedTime())), httpSession.getMaxInactiveInterval());

        String username = request.getParameter("username");
        logger.debug("从请求中获取 username: {}", username);

        // 查询Redis共享session中是否有当前用户信息，若有则通过拦截
        String userInfo = (String) httpSession.getAttribute(username);
        if (StringUtils.isEmpty(userInfo)) {
            logger.debug("Redis共享Session中无此用户信息 username: {}", username);

            response.addHeader("100001", "用户未登陆");

            return false;
        }

        logger.debug("---------- 安全拦截器 预处理 完成 ----------");
        return super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        logger.debug("---------- 安全拦截器 后处理 开始 ----------");
        super.postHandle(request, response, handler, modelAndView);
        logger.debug("---------- 安全拦截器 后处理 完成 ----------");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        logger.debug("---------- 安全拦截器 请求完成后处理 开始 ----------");
        super.afterCompletion(request, response, handler, ex);
        logger.debug("---------- 安全拦截器 请求完成后处理 完成 ----------");
    }

    @Override
    public void afterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.debug("---------- 安全拦截器 并发处理启动后处理 开始 ----------");
        super.afterConcurrentHandlingStarted(request, response, handler);
        logger.debug("---------- 安全拦截器 并发处理启动后处理 开始 ----------");
    }
}
