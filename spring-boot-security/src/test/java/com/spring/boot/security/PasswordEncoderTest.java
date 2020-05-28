package com.spring.boot.security;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordEncoderTest {
    @Test
    public void encode() {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

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
