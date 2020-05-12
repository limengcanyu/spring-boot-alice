package com.spring.boot.redis.utils;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
public class RedisTools {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    public boolean expire(String key, long timeout, TimeUnit unit) {
        try {
            if (timeout > 0) {
                stringRedisTemplate.expire(key, timeout, unit);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public void setString(String key, String value) {
        stringRedisTemplate.opsForValue().set(key, value);
    }

    public void setString(String key, String value, long timeout, TimeUnit unit) {
        stringRedisTemplate.opsForValue().set(key, value, timeout, unit);
    }

    public String getString(String key) {
        return stringRedisTemplate.opsForValue().get(key);
    }

    public void deleteString(String key) {
        stringRedisTemplate.delete(key);
    }

    public void deleteStrings(List<String> keys) {
        stringRedisTemplate.delete(keys);
    }

    public void setObject(String key, Object value) {
        stringRedisTemplate.opsForValue().set(key, JSONObject.toJSONString(value));
    }

    public void setObject(String key, Object value, long timeout, TimeUnit unit) {
        stringRedisTemplate.opsForValue().set(key, JSONObject.toJSONString(value), timeout, unit);
    }

    public <T> T getObject(String key, Class<T> valueClass) {
        try {
            String value = stringRedisTemplate.opsForValue().get(key);
            return JSONObject.parseObject(value, valueClass);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public <T> void setList(String key, List<T> valueList) {
        stringRedisTemplate.opsForValue().set(key, JSONObject.toJSONString(valueList));
    }

    public <T> void setList(String key, List<T> valueList, long timeout, TimeUnit unit) {
        stringRedisTemplate.opsForValue().set(key, JSONObject.toJSONString(valueList), timeout, unit);
    }

    public <T> List<T> getList(String key, Class<T> valueClass) {
        try {
            String value = stringRedisTemplate.opsForValue().get(key);
            return JSONObject.parseArray(value, valueClass);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void increment(String key, long delta) {
        stringRedisTemplate.opsForValue().increment(key, delta);
    }


}
