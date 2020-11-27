package com.spring.boot.security.custom;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

/**
 * <p>Description: </p>
 *
 * @author Rock Jiang
 * @version 1.0
 * @date 2018/4/18 0018
 */
@SpringBootTest
public class GenericJackson2JsonRedisSerializerTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void serializeTest() {
    }
}
