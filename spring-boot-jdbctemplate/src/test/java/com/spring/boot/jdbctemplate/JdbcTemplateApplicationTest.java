package com.spring.boot.jdbctemplate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootTest
public class JdbcTemplateApplicationTest {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void selectOne() {
        String username = jdbcTemplate.queryForObject("select username from sys_user where user_id = 3", String.class);
        System.out.println("username: " + username);
    }
}
