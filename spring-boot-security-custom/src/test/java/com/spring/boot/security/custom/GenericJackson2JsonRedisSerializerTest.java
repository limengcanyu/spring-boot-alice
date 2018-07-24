package com.spring.boot.security.custom;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * <p>Description: </p>
 *
 * @author Rock Jiang
 * @version 1.0
 * @date 2018/4/18 0018
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SecurityCustomApplication.class)
public class GenericJackson2JsonRedisSerializerTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void serializeTest() {
    }
}
