package com.spring.boot.redis.config;

import com.alibaba.fastjson.support.spring.GenericFastJsonRedisSerializer;
import com.spring.boot.redis.utils.RedisUtils;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;

/**
 * <p>Description: Redis Config</p>
 *
 * @author rock.jiang
 * date 2019/06/20
 */

@Configuration
public class RedisConfig {

    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);

        // 字符串序列化
        RedisSerializer<String> stringRedisSerializer = new StringRedisSerializer();

        // 对象序列化
//        RedisSerializer<Object> objectRedisSerializer = new GenericJackson2JsonRedisSerializer();
        RedisSerializer<Object> fastJsonRedisSerializer = new GenericFastJsonRedisSerializer();
//        RedisSerializer<Object> objectRedisSerializer = new JdkSerializationRedisSerializer();

        // key 使用字符串序列化
        redisTemplate.setKeySerializer(RedisSerializer.string());
        redisTemplate.setHashKeySerializer(RedisSerializer.string());

        // value 使用字符串序列化
        redisTemplate.setValueSerializer(RedisSerializer.string()); // 使用StringRedisSerializer才能进行valueOpsIncrement操作
        redisTemplate.setHashValueSerializer(RedisSerializer.json());

        redisTemplate.afterPropertiesSet();

        return redisTemplate;
    }

    @Bean
    public CacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
        RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofHours(1));
        return RedisCacheManager
                .builder(RedisCacheWriter.nonLockingRedisCacheWriter(redisConnectionFactory))
                .cacheDefaults(redisCacheConfiguration).build();
    }

}
