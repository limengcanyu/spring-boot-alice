package com.spring.boot.data.redis;

import com.spring.boot.data.redis.entity.UserInfo;
import com.spring.boot.data.redis.util.RedisUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * comment
 *
 * @author rock
 * @date 2019/6/9
 */
@SuppressWarnings("unchecked")
@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisUtilsTest {

    @Autowired
    private RedisUtils redisUtils;

    @Test
    public void jacksonSerializeSet() {
        String key = "user3";
        UserInfo userInfo = new UserInfo(1, "rock", "123456");

        // 设置值
        redisUtils.jacksonSerializeSet(key, userInfo);
    }

    @Test
    public void jacksonSerializeGet() {
        String key = "user3";

        // 取出值
        UserInfo userInfo = (UserInfo)redisUtils.jacksonSerializeGet(key);
        System.out.println(userInfo);
    }

    @Test
    public void fastJsonSerializeSet() {
        String key = "user4";
        UserInfo userInfo = new UserInfo(1, "rock", "123456");

        // 设置值
        redisUtils.fastJsonSerializeSet(key, userInfo);
    }

    @Test
    public void fastJsonSerializeGet() {
        String key = "user4";

        // 取出值
        UserInfo userInfo = (UserInfo)redisUtils.fastJsonSerializeGet(key);
        System.out.println(userInfo);
    }

    @Test
    public void set() {
        String key = "name";
        String value = "111111";

        redisUtils.set(key, value);
    }

    @Test
    public void get() {
        String key = "name";

        String value = (String) redisUtils.get(key);
        System.out.println("value: " + value);
    }

    @Test
    public void leftPush() {
        String key = "password";

        // 一个key对应多个值
        redisUtils.leftPush(key, "123456");
        redisUtils.leftPush(key, "456789");
        redisUtils.leftPush(key, "789123");
    }

    @Test
    public void leftPop() {
        String key = "password";

        // 取出最后加入的值
        String value = (String) redisUtils.leftPop(key);
        System.out.println("value: " + value);
    }
}
