package com.spring.boot.mongo.entity;

import lombok.Data;
import org.springframework.data.annotation.Version;

@Data
public class Person {
    private String id;
    private String name;
    private int age;
    /**
     * 乐观锁字段
     */
    @Version
    Long version;

    public Person() {
    }

    public Person(String name) {
        this.name = name;
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

}
