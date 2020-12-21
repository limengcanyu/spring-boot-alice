package com.spring.boot.redis;

import com.spring.boot.redis.entity.User;
import com.spring.boot.redis.utils.RedisUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * <p>Description: RedisUtils Test</p>
 *
 * @author rock.jiang
 * date 2019/06/20
 */
@SpringBootTest
public class RedisUtilsTest {
    @Autowired
    private RedisUtils redisUtils;

    @Test
    public void ops() {
        String key = "key001";
        String value = "value001";
        redisUtils.setString(key, value);

        System.out.println("value: " + redisUtils.getString("key001"));

        key = "key002";
        redisUtils.setObject(key, new User("user001", "墨月1", "1234567890"), 10, TimeUnit.SECONDS);

        User redisUser = (User) redisUtils.getObject("key002");
        System.out.println("user: " + redisUser);

        key = "key004";
        List<User> userList = new ArrayList<>();
        userList.add(new User("user002", "墨月2", "1234567890"));
        userList.add(new User("user003", "墨月3", "1234567890"));
        userList.add(new User("user004", "墨月4", "1234567890"));
        redisUtils.setList(key, userList);

        List<User> redisUserList = redisUtils.getList("key004");
        System.out.println("userList: " + redisUserList);

        Map<String, User> map = new HashMap<>();
        map.put("user002", new User("user002", "墨月2", "1234567890"));
        map.put("user003", new User("user003", "墨月3", "1234567890"));
        redisUtils.setObject("key005", map);

        Map<String, User> redisMap = (Map<String, User>) redisUtils.getObject("key005");
        System.out.println("map: " + redisMap);
    }

}
