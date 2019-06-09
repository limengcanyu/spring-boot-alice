package com.spring.boot.data.redis.entity;

import lombok.Data;

/**
 * <p>Description: </p>
 *
 * @author Rock Jiang
 * @version 1.0
 * @date 2018/4/12 0012
 */
@Data
public class UserInfo {
    private Integer userId;
    private String username;
    private String password;

    public UserInfo() {
    }

    public UserInfo(Integer userId, String username, String password) {
        this.userId = userId;
        this.username = username;
        this.password = password;
    }
}
