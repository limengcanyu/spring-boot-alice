package com.spring.boot.mybatisplus.entity;

import lombok.Data;

/**
 * comment
 *
 * @author rock
 * @date 2019/6/22
 */
@Data
public class User {
    private Long id;
    private String name;
    private Integer age;
    private String email;
}
