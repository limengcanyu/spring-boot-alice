package com.spring.boot.neo4j.entity;

import lombok.Data;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;

import java.io.Serializable;

@Data
@NodeEntity
public class City implements Serializable {

    @Id
    @GeneratedValue
    private Long id;


    private String name;

    private String state;

    protected City() {
        // no-args constructor required by JPA spec
        // this one is protected since it shouldn't be used directly
    }

    public City(String name, String state) {
        this.name = name;
        this.state = state;
    }

}
