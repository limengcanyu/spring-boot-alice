package com.spring.boot.data.redis;

import com.spring.boot.data.redis.dao.entity.UserInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.types.RedisClientInfo;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * <p>Description: </p>
 *
 * @author Rock Jiang
 * @version 1.0
 * @date 2018/4/12 0012
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = RedisApplication.class)
public class RedisTemplateTest {
    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    public void test(){
        System.out.println(stringRedisTemplate.isEnableDefaultSerializer());

        List<RedisClientInfo> clientInfoList = stringRedisTemplate.getClientList();
        System.out.println("clientInfoList.size: " + clientInfoList.size());
        if (!CollectionUtils.isEmpty(clientInfoList)) {
            clientInfoList.stream().forEach(redisClientInfo -> System.out.println(
                    "address port: " + redisClientInfo.getAddressPort() +
                            " age: " + redisClientInfo.getAge() +
                            " idle: " + redisClientInfo.getIdle()
            ));
        }

        stringRedisTemplate.opsForValue().set("nanfengbujing", "nanfengbujing", 30, TimeUnit.SECONDS);
    }

    @Test
    public void test2(){
        RedisSerializer redisSerializer = new GenericJackson2JsonRedisSerializer();
        redisTemplate.setValueSerializer(redisSerializer);

        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(1);
        userInfo.setUsername("nanfengbujing");
        userInfo.setPassword("123456");
        redisTemplate.opsForValue().set("nanfeng", userInfo);
    }
}
