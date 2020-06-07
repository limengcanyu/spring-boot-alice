package com.spring.boot.security;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * <p>Description: </p>
 *
 * @author Rock Jiang
 * @version 1.0
 * @date 2018/4/4 0004
 */
@SpringBootTest
public class BCryptPasswordEncoderTest {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Test
    public void encodeTest() {
         passwordEncoder = new BCryptPasswordEncoder();
        System.out.println("123456 encode: " + passwordEncoder.encode("123456"));
    }

    @Test
    public void encode() {
        for (int i = 0; i < 10; i++) {
            long startTime = System.currentTimeMillis();
            String encodedPassword = passwordEncoder.encode("1234567890");
            System.out.println("encodedPassword: " + encodedPassword);

            System.out.println("match result: " + passwordEncoder.matches("1234567890", encodedPassword));
            long endTime = System.currentTimeMillis();
            System.out.println("cost time: " + (endTime - startTime) / 1000 + " seconds");
        }
    }

}
