package com.spring.boot.security;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * <p>Description: </p>
 *
 * @author Rock Jiang
 * @version 1.0
 * @date 2018/4/4 0004
 */
public class BCryptPasswordEncoderTest {

    @Test
    public void encodeTest() {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        System.out.println("123456 encode: " + passwordEncoder.encode("123456"));
    }
}
