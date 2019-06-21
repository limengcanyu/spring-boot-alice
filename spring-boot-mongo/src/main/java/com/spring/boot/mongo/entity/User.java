package com.spring.boot.mongo.entity;

import lombok.Data;

import java.util.List;

/**
 * <p>Description: </p>
 *
 * @author rock.jiang
 * date 2019/06/21
 */
@Data
public class User {
    private String id;
    private String userId;
    private String userName;
    private String password;
    private List<Role> roleList;

    public User() {
    }

    public User(String userId, String userName, String password, List<Role> roleList) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.roleList = roleList;
    }
}
