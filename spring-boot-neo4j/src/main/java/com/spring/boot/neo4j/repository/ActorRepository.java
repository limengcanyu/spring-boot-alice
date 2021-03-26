package com.spring.boot.neo4j.repository;

import com.spring.boot.neo4j.entity.Actor;
import org.springframework.data.repository.CrudRepository;

public interface ActorRepository extends CrudRepository<Actor, Long> {

    Actor findByName(String name);

}
