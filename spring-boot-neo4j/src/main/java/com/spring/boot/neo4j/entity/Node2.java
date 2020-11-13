//package com.spring.boot.neo4j.entity;
//
//import lombok.Data;
//import org.neo4j.ogm.annotation.GeneratedValue;
//import org.neo4j.ogm.annotation.Id;
//import org.neo4j.ogm.annotation.NodeEntity;
//import org.neo4j.ogm.annotation.Relationship;
//import org.springframework.util.CollectionUtils;
//
//import java.util.HashSet;
//import java.util.Set;
//
//@Data
//@NodeEntity
//public class Node2 {
//    @Id
//    @GeneratedValue
//    private Long id;
//
//    private String name;
//
//    public Node2() {
//    }
//
//    public Node2(String name) {
//        this.name = name;
//    }
//
//    @Relationship(type = "REFERENCE_RELATIONSHIP")
//    public Set<Node3> node3s;
//
//    public void addNode3(Node3 node3) {
//        if (CollectionUtils.isEmpty(node3s)) {
//            node3s = new HashSet<>();
//        }
//        node3s.add(node3);
//    }
//
//}
