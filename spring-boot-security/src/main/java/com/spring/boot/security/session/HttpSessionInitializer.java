package com.spring.boot.security.session;

import org.springframework.session.web.context.AbstractHttpSessionApplicationInitializer;

/**
 * <p>Description: ensure that our Servlet Container (i.e. Tomcat) uses our springSessionRepositoryFilter for every request</p>
 *
 * @author Rock Jiang
 * @version 1.0
 * @date 2018/4/10 0010
 */
public class HttpSessionInitializer extends AbstractHttpSessionApplicationInitializer {
    public HttpSessionInitializer() {
        super(RedisHttpSessionConfig.class);
    }
}
