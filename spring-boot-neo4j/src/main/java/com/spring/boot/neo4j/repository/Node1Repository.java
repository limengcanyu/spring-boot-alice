package com.spring.boot.neo4j.repository;

import com.spring.boot.neo4j.entity.Node1;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface Node1Repository extends Neo4jRepository<Node1, Long> {

    Node1 findByName(String name);

}
