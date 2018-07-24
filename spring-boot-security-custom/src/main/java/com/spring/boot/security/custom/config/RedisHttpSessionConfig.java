package com.spring.boot.security.custom.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

import javax.servlet.http.HttpSessionListener;

/**
 * <p>Description: </p>
 *
 * @author Rock Jiang
 * @version 1.0
 * @date 2018/4/18 0018
 */
// This creates a Spring Bean with the name of springSessionRepositoryFilter that implements Filter.
// The filter is what is in charge of replacing the HttpSession implementation to be backed by Spring Session
@EnableRedisHttpSession
@Configuration
public class RedisHttpSessionConfig {
    /**
     * create a RedisConnectionFactory that connects Spring Session to the Redis Server
     *
     * @return
     */
    @Bean
    public LettuceConnectionFactory connectionFactory() {
        return new LettuceConnectionFactory();
    }

    @Bean
    public HttpSessionListener httpSessionListener() {
        return new HttpSessionMonitorListener();
    }

}
