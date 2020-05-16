package com.spring.boot.elasticsearch.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

import java.time.LocalDateTime;

@Document(indexName="person")
@Data
public class Person {
    @Id
    private String id;

    private String firstName;

    private String lastName;

    private String createTime;

    @Version
    private Long version;

    public Person() {
    }

    public Person(String firstName, String lastName, String createTime) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.createTime = createTime;
    }
}
