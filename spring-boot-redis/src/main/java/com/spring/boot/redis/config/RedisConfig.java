package com.spring.boot.redis.config;

import com.alibaba.fastjson.support.spring.GenericFastJsonRedisSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * <p>Description: Redis Config</p>
 *
 * @author rock.jiang
 * date 2019/06/20
 */

@Configuration
public class RedisConfig {

//    /**
//     * Lettuce Connection Factory
//     *
//     * @return
//     */
//    @Bean
//    public LettuceConnectionFactory redisConnectionFactory() {
//        RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration("server", 6379);
//
//        return new LettuceConnectionFactory();
//    }

//    /**
//     * Jedis Connection Factory
//     *
//     * @return
//     */
//    @Bean
//    public JedisConnectionFactory redisConnectionFactory() {
//        RedisStandaloneConfiguration config = new RedisStandaloneConfiguration("server", 6379);
//        return new JedisConnectionFactory(config);
//    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);

        // 字符串序列化
        RedisSerializer<String> stringRedisSerializer = new StringRedisSerializer();

        // 对象序列化
//        RedisSerializer<Object> objectRedisSerializer = new GenericJackson2JsonRedisSerializer();
        RedisSerializer<Object> fastJsonRedisSerializer = new GenericFastJsonRedisSerializer();
//        RedisSerializer<Object> objectRedisSerializer = new JdkSerializationRedisSerializer();

        template.setKeySerializer(stringRedisSerializer);
        template.setValueSerializer(stringRedisSerializer); // 使用StringRedisSerializer才能进行valueOpsIncrement操作

        template.setHashKeySerializer(stringRedisSerializer);
        template.setHashValueSerializer(fastJsonRedisSerializer);

        template.afterPropertiesSet();

        return template;
    }
}
