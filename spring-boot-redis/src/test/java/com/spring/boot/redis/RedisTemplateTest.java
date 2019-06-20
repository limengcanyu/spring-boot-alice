package com.spring.boot.redis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.*;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * <p>Description: RedisTemplate Test</p>
 *
 * @author rock.jiang
 * date 2019/06/20
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTemplateTest {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Resource(name="redisTemplate")
    private ValueOperations<String, Object> valueOps;

    @Resource(name="redisTemplate")
    private ListOperations<String, Object> listOps;

    @Resource(name="redisTemplate")
    private SetOperations<String, Object> setOps;

    @Resource(name="redisTemplate")
    private ZSetOperations<String, Object> zSetOps;

    @Resource(name="redisTemplate")
    private  GeoOperations<String, Object> geoOps;

//    @Resource(name="redisTemplate")
//    private  HyperLogLogOperations<String, Object> hllOps;

    @Test
    public void test() {
        System.out.println();
    }
}
