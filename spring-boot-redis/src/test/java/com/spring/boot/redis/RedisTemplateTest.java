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

    @Resource(name = "redisTemplate")
    private ValueOperations<String, Object> valueOps;

    @Resource(name = "redisTemplate")
    private ListOperations<String, Object> listOps;

    @Resource(name = "redisTemplate")
    private SetOperations<String, Object> setOps;

    @Resource(name = "redisTemplate")
    private ZSetOperations<String, Object> zSetOps;

    @Resource(name = "redisTemplate")
    private GeoOperations<String, Object> geoOps;

//    @Resource(name="redisTemplate")
//    private  HyperLogLogOperations<String, Object> hllOps;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    public RedisTemplateTest() {
    }

    @Test
    public void test() {
        String key = "CalculateASynTax:" + "tenant_000001" + ":" + "company_000001" + ":" + "2019-11";
        String requestId = "request_000001";
        stringRedisTemplate.opsForValue().set(key, requestId);
        key = stringRedisTemplate.opsForValue().get(key);
        System.out.println(key);

        key = "CalculateASynTax:" + "tenant_000001" + ":" + "company_000001" + ":" + "2019-12";
        requestId = "request_000002";
        stringRedisTemplate.opsForValue().set(key, requestId);
        key = stringRedisTemplate.opsForValue().get(key);
        System.out.println(key);

        key = "CalculateASynTax:" + "tenant_000001" + ":" + "company_000001" + ":" + "2019-11";
        requestId = "request_000003";
        stringRedisTemplate.opsForValue().set(key, requestId);
        key = stringRedisTemplate.opsForValue().get(key);
        System.out.println(key);
    }
}
