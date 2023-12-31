package com.spring.boot.neo4j.entity;

import lombok.Data;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;
import org.springframework.util.CollectionUtils;

import java.util.HashSet;
import java.util.Set;

@Data
@NodeEntity
public class Actor {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String born;

    public Actor() {
    }

    public Actor(String name, String born) {
        this.name = name;
        this.born = born;
    }

    @Relationship(type = "LIVED_IN", direction = Relationship.INCOMING)
    public Set<City> cities;

    public void addCity(City city) {
        if (CollectionUtils.isEmpty(cities)) {
            cities = new HashSet<>();
        }
        cities.add(city);
    }

    @Relationship(type = "ACTED_IN", direction = Relationship.OUTGOING)
    public Set<Movie> actors;

    public void addActor(Movie movie) {
        if (CollectionUtils.isEmpty(actors)) {
            actors = new HashSet<>();
        }
        actors.add(movie);
    }

    @Relationship(type = "DIRECTED", direction = Relationship.OUTGOING)
    public Set<Movie> directors;

    public void addDirector(Movie movie) {
        if (CollectionUtils.isEmpty(directors)) {
            directors = new HashSet<>();
        }
        directors.add(movie);
    }

}
