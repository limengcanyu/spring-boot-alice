//package com.spring.boot.neo4j.entity;
//
//import lombok.Data;
//import org.neo4j.ogm.annotation.GeneratedValue;
//import org.neo4j.ogm.annotation.Id;
//import org.neo4j.ogm.annotation.NodeEntity;
//import org.neo4j.ogm.annotation.Property;
//
//import java.io.Serializable;
//
//@Data
//@NodeEntity(label = "City")
//public class City implements Serializable {
//
//    @Id
//    @GeneratedValue
//    private Long id;
//
//    @Property(name = "name")
//    private String name;
//
//    @Property(name = "state")
//    private String state;
//
//    protected City() {
//    }
//
//    public City(String name, String state) {
//        this.name = name;
//        this.state = state;
//    }
//
//}
