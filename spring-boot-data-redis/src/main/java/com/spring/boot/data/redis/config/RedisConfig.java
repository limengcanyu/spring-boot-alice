package com.spring.boot.data.redis.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;

/**
 * <p>Description: Redis配置类</p>
 *
 * @author Rock Jiang
 * @version 1.0
 * @date 2018/4/12 0012
 */
@Configuration
public class RedisConfig {
    @Bean
    JedisConnectionFactory jedisConnectionFactory(){
        JedisConnectionFactory connectionFactory = new JedisConnectionFactory();
        return connectionFactory;
    }
}
