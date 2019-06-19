package com.spring.boot.security;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PasswordEncoderTest {
    @Autowired
    private PasswordEncoder passwordEncoder;

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
