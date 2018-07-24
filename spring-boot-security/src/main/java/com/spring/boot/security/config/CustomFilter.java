package com.spring.boot.security.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * <p>Description: 自定义Filter</p>
 *
 * @author Rock Jiang
 * @version 1.0
 * @date 2018/4/12 0012
 */
@Component("customFilter")
public class CustomFilter implements Filter {
    private static final Logger logger = LoggerFactory.getLogger(CustomFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        logger.debug("--------------------自定义Filter--------------------");
        HttpServletRequest servletRequest = (HttpServletRequest) request;
        HttpSession httpSession = servletRequest.getSession();
        logger.debug("http session id: {}", httpSession.getId());
    }

    @Override
    public void destroy() {

    }
}
