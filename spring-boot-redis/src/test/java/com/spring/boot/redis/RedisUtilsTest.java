package com.spring.boot.redis;

import com.alibaba.fastjson.JSONObject;
import com.spring.boot.redis.entity.User;
import com.spring.boot.redis.utils.RedisUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * <p>Description: RedisUtils Test</p>
 *
 * @author rock.jiang
 * date 2019/06/20
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisUtilsTest {
    @Autowired
    private RedisUtils redisUtils;

    @Test
    public void valueOps() {
        String key = "key001";
        String value = "value001";
        redisUtils.valueOpsSet(key, value);

        key = "key002";
        User user = new User("user001", "墨月1", "1234567890");
        redisUtils.valueOpsSet(key, JSONObject.toJSONString(user), 10, TimeUnit.SECONDS);

        key = "key003";
        value = "1";
        redisUtils.valueOpsSet(key, value);
        for (int i = 0; i < 5; i++) {
            Long result = redisUtils.valueOpsIncrement(key, 1);
            System.out.println("increment result: " + result);
        }

        value = (String) redisUtils.valueOpsGet("key001");
        System.out.println("value: " + value);

        user = (User) redisUtils.valueOpsGet("key002");
        System.out.println("user: " + user);
    }

    @Test
    public void listOps() {
        String key = "key004";
        List<User> userList = new ArrayList<>();
        User user = new User("user002", "墨月2", "1234567890");
        userList.add(user);
        user = new User("user003", "墨月3", "1234567890");
        userList.add(user);
        user = new User("user004", "墨月4", "1234567890");
        userList.add(user);
        redisUtils.listOpsLeftPushAll(key, JSONObject.toJSONString(userList));
    }

    @Test
    public void hashOps() {
        String key = "key005";

        String hashKey = "hashKey005";
        List<User> userList = new ArrayList<>();
        User user = new User("user005", "墨月5", "1234567890");
        userList.add(user);
        user = new User("user006", "墨月6", "1234567890");
        userList.add(user);
        user = new User("user007", "墨月7", "1234567890");
        userList.add(user);
        redisUtils.hashOpsPut(key, hashKey, userList);

        userList = (List<User>) redisUtils.hashOpsGet(key, hashKey);
        System.out.println(userList);

        hashKey = "hashKey006";
        userList = new ArrayList<>();
        user = new User("user005", "墨月5", "1234567890");
        userList.add(user);
        user = new User("user006", "墨月6", "1234567890");
        userList.add(user);
        user = new User("user007", "墨月7", "1234567890");
        userList.add(user);
        redisUtils.hashOpsPut(key, hashKey, userList);

        userList = (List<User>) redisUtils.hashOpsGet(key, hashKey);
        System.out.println(userList);
    }
}
