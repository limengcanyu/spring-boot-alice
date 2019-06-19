package com.spring.boot.data.redis.util;

import com.alibaba.fastjson.support.spring.FastJsonRedisSerializer;
import com.spring.boot.data.redis.entity.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.*;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * comment
 *
 * @author rock
 * @date 2019/6/9
 */
@Component
public class RedisUtils<K, V> {
    @Autowired
    private RedisTemplate<K, V> redisTemplate;

    @Resource(name="redisTemplate")
    private ValueOperations<K, V> valueOps;

    @Resource(name="redisTemplate")
    private ListOperations<K, V> listOps;

    @Resource(name="redisTemplate")
    private SetOperations<K, V> setOps;

    @Resource(name="redisTemplate")
    private ZSetOperations<K, V> zSetOps;

    @Resource(name="redisTemplate")
    private GeoOperations<K, V> geoOps;

//    @Resource(name="redisTemplate")
//    private HyperLogLogOperations<String, String> hllOps;

    /**
     * jackson Serialize Set
     *
     * @param key
     * @param value
     */
    public void jacksonSerializeSet(K key, V value) {
        RedisSerializer<UserInfo> redisSerializer = new Jackson2JsonRedisSerializer<>(UserInfo.class);

        redisTemplate.setKeySerializer(new StringRedisSerializer()); // 设置key序列化器
        redisTemplate.setValueSerializer(redisSerializer); // 设置value序列化器

        RedisSerializer keySerializer = redisTemplate.getKeySerializer();
        RedisSerializer valueSerializer = redisTemplate.getValueSerializer();
        System.out.println("keySerializer：" + keySerializer + " valueSerializer: " + valueSerializer);


        // 存放值
        redisTemplate.opsForValue().set(key, value);
    }

    /**
     * jackson Serialize Get
     *
     * @param key
     * @return
     */
    public V jacksonSerializeGet(K key) {
        RedisSerializer<UserInfo> redisSerializer = new Jackson2JsonRedisSerializer<>(UserInfo.class);

        redisTemplate.setKeySerializer(new StringRedisSerializer()); // 设置key序列化器
        redisTemplate.setValueSerializer(redisSerializer); // 设置value序列化器

        // 取出值
        return redisTemplate.opsForValue().get(key);
    }

    /**
     * fastJson Serialize Set
     *
     * @param key
     * @param value
     */
    public void fastJsonSerializeSet(K key, V value) {
        RedisSerializer<UserInfo> redisSerializer = new FastJsonRedisSerializer<>(UserInfo.class);

        redisTemplate.setKeySerializer(new StringRedisSerializer()); // 设置key序列化器
        redisTemplate.setValueSerializer(redisSerializer); // 设置value序列化器

        RedisSerializer keySerializer = redisTemplate.getKeySerializer();
        RedisSerializer valueSerializer = redisTemplate.getValueSerializer();
        System.out.println("keySerializer：" + keySerializer + " valueSerializer: " + valueSerializer);

        // 存放值
        redisTemplate.opsForValue().set(key, value);
    }

    /**
     * fastJson Serialize Get
     *
     * @param key
     * @return
     */
    public V fastJsonSerializeGet(K key) {
        RedisSerializer<UserInfo> redisSerializer = new FastJsonRedisSerializer<>(UserInfo.class);

        redisTemplate.setKeySerializer(new StringRedisSerializer()); // 设置key序列化器
        redisTemplate.setValueSerializer(redisSerializer); // 设置value序列化器

        // 存放值
        return redisTemplate.opsForValue().get(key);
    }

    /**
     * ValueOperations set
     *
     * @param key
     * @param value
     */
    public void set(K key, V value) {
        RedisSerializer keySerializer = redisTemplate.getKeySerializer();
        RedisSerializer valueSerializer = redisTemplate.getValueSerializer();
        System.out.println("keySerializer：" + keySerializer + " valueSerializer: " + valueSerializer);

        valueOps.set(key, value);
    }

    /**
     * ValueOperations get
     *
     * @param key
     * @return
     */
    public V get(Object key) {
        return valueOps.get(key);
    }

    /**
     * ListOperations leftPush
     *
     * @param key
     * @param value
     * @return
     */
    public Long leftPush(K key, V value) {
        RedisSerializer keySerializer = redisTemplate.getKeySerializer();
        RedisSerializer valueSerializer = redisTemplate.getValueSerializer();
        System.out.println("keySerializer：" + keySerializer + " valueSerializer: " + valueSerializer);

        return listOps.leftPush(key, value);
    }

    /**
     * ListOperations leftPop
     *
     * @param key
     * @return
     */
    public V leftPop(K key) {
        return listOps.leftPop(key);
    }
}
