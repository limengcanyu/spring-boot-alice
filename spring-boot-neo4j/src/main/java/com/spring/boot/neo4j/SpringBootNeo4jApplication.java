package com.spring.boot.neo4j;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EntityScan(basePackages = "com.spring.boot.neo4j.entity")
@EnableNeo4jRepositories(basePackages = "com.spring.boot.neo4j.repository")
@EnableTransactionManagement
@SpringBootApplication
public class SpringBootNeo4jApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootNeo4jApplication.class, args);
    }

}
