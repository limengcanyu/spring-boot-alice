package com.spring.boot.data.redis;

import com.alibaba.fastjson.support.spring.FastJsonRedisSerializer;
import com.spring.boot.data.redis.entity.UserInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * <p>Description: RedisTemplate RedisSerializer</p>
 *
 * @author Rock Jiang
 * @version 1.0
 * @date 2018/4/12 0012
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTemplateSerializerTest {
    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void jackson2JsonRedisSerializer() {
        RedisSerializer<UserInfo> redisSerializer = new Jackson2JsonRedisSerializer<>(UserInfo.class);

        UserInfo userInfo = new UserInfo(1, "rock", "123456");

        redisTemplate.setKeySerializer(new StringRedisSerializer()); // 设置key序列化器
        redisTemplate.setValueSerializer(redisSerializer); // 设置value序列化器

        // 存放值
        redisTemplate.opsForValue().set("user1", userInfo);

        // 取出值
        UserInfo user1 = (UserInfo)redisTemplate.opsForValue().get("user1");
        System.out.println("user1: " + user1);

        // 修改对象
        userInfo.setUsername("alita");

        // 存放值
        redisTemplate.opsForValue().set("user1", userInfo);

        // 取出值
        user1 = (UserInfo)redisTemplate.opsForValue().get("user1");
        System.out.println("user1: " + user1);

    }

    @Test
    public void fastJsonRedisSerializer() {
        RedisSerializer<UserInfo> redisSerializer = new FastJsonRedisSerializer<>(UserInfo.class);

        UserInfo userInfo = new UserInfo(1, "rock", "123456");

        redisTemplate.setKeySerializer(new StringRedisSerializer()); // 设置key序列化器
        redisTemplate.setValueSerializer(redisSerializer); // 设置value序列化器

        // 存放值
        redisTemplate.opsForValue().set("user2", userInfo);

        // 取出值
        UserInfo user1 = (UserInfo)redisTemplate.opsForValue().get("user2");
        System.out.println("user2: " + user1);

        // 修改对象
        userInfo.setUsername("alita");

        // 存放值
        redisTemplate.opsForValue().set("user2", userInfo);

        // 取出值
        user1 = (UserInfo)redisTemplate.opsForValue().get("user2");
        System.out.println("user2: " + user1);

    }

}
