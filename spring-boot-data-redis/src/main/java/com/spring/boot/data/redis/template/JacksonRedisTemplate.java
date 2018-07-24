package com.spring.boot.data.redis.template;//package com.spring.boot.redis.template;
//
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
//import org.springframework.data.redis.serializer.RedisSerializer;
//
///**
// * <p>Description: </p>
// *
// * @author Rock Jiang
// * @version 1.0
// * @date 2018/4/12 0012
// */
//public class JacksonRedisTemplate<T> extends RedisTemplate<String, String> {
//    public JacksonRedisTemplate() {
//        RedisSerializer<String> stringSerializer = new Jackson2JsonRedisSerializer<T>();
//        setKeySerializer(stringSerializer);
//        setValueSerializer(stringSerializer);
//        setHashKeySerializer(stringSerializer);
//        setHashValueSerializer(stringSerializer);
//    }
//}
