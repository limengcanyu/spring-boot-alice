package com.vue.element.admin.entity;

import lombok.Data;

import java.util.List;

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

    private String name;
    private String avatar;
    private String introduction;
    private List<String> roles;
    private List<String> routePaths;
    private List<String> routeNames;

    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
