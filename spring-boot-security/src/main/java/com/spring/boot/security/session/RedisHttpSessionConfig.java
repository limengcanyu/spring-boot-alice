package com.spring.boot.security.session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * <p>Description: Spring Session Redis Http Session 配置</p>
 *
 * @author Rock Jiang
 * @version 1.0
 * @date 2018/4/10 0010
 */
@Configuration
// setting up Spring Session backed by Redis
// creates a Spring Bean with the name of springSessionRepositoryFilter that implements Filter
// The filter is what is in charge of replacing the HttpSession implementation to be backed by Spring Session
@EnableRedisHttpSession
public class RedisHttpSessionConfig {
    private static final Logger logger = LoggerFactory.getLogger(RedisHttpSessionConfig.class);

    /**
     * Lettuce Redis连接工厂: 配置Redis连接信息
     *
     * @return
     */
    @Bean
    public LettuceConnectionFactory connectionFactory() {
        logger.debug("Lettuce Redis连接工厂: 配置Redis连接信息");
        return new LettuceConnectionFactory();
    }
}
