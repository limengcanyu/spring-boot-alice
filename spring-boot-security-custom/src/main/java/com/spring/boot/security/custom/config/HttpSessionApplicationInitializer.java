package com.spring.boot.security.custom.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.session.web.context.AbstractHttpSessionApplicationInitializer;

import javax.servlet.ServletContext;

/**
 * <p>Description: </p>
 * extend AbstractHttpSessionApplicationInitializer.
 * This ensures that the Spring Bean by the name springSessionRepositoryFilter is registered with our Servlet Container for every request.
 *
 * @author Rock Jiang
 * @version 1.0
 * @date 2018/4/18 0018
 */
public class HttpSessionApplicationInitializer extends AbstractHttpSessionApplicationInitializer {
    private static final Logger logger = LoggerFactory.getLogger(HttpSessionApplicationInitializer.class);

    /**
     * ensure Spring loads Config
     */
    public HttpSessionApplicationInitializer() {
        super(RedisHttpSessionConfig.class);
    }

    /**
     * 注入sessionRepositoryFilter前业务处理
     *
     * @param servletContext
     */
    @Override
    protected void beforeSessionRepositoryFilter(ServletContext servletContext) {
        super.beforeSessionRepositoryFilter(servletContext);
        logger.debug("---------- 注入sessionRepositoryFilter前业务处理 ----------");
    }

    /**
     * 注入sessionRepositoryFilter后业务处理
     *
     * @param servletContext
     */
    @Override
    protected void afterSessionRepositoryFilter(ServletContext servletContext) {
        super.afterSessionRepositoryFilter(servletContext);
        logger.debug("---------- 注入sessionRepositoryFilter后业务处理 ----------");
    }
}
