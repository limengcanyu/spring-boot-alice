package com.spring.boot.neo4j.repository;

import java.util.Optional;

import com.spring.boot.neo4j.entity.City;
import org.springframework.data.neo4j.repository.*;

public interface CityRepository extends Neo4jRepository<City, Long> {

    Optional<City> findOneByNameAndState(String name, String state);

}
