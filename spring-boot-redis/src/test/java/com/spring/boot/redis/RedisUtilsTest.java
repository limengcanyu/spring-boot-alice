package com.spring.boot.redis;

import com.spring.boot.redis.entity.User;
import com.spring.boot.redis.utils.RedisUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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
    public void set() {
        String key = "key001";
        String value = "value001";
        redisUtils.set(key, value);

        key = "key002";
        User user = new User("user001", "墨月", "1234567890");
        redisUtils.set(key, value);
    }
}
