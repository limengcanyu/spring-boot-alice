package com.spring.boot.security.entity;

import lombok.Data;

/**
 * <p>Description: </p>
 *
 * @author rock
 * date 2019/06/23
 */
@Data
public class User {
    private String username;
    private String password;

    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
