package com.spring.boot.jdbctemplate.entity;

import lombok.Data;

/**
 * <p>Description: </p>
 *
 * @author Rock Jiang
 * @version 1.0
 * @date 2019/3/20 0020
 */
@Data
public class SysUser {
    /**
     * 用户ID
     */
    private Integer userId;
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 中文名称
     */
    private String chineseName;
    /**
     * 性别：1-男，2-女
     */
    private Integer sex;

}
