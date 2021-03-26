package com.spring.boot.neo4j.repository;

import com.spring.boot.neo4j.entity.Movie;
import org.springframework.data.repository.CrudRepository;

public interface MovieRepository extends CrudRepository<Movie, Long> {

    Movie findByTitle(String title);

}
