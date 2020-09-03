package com.spring.boot.neo4j;

import com.spring.boot.neo4j.entity.City;
import com.spring.boot.neo4j.repository.CityRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.neo4j.DataNeo4jTest;
import org.springframework.boot.test.context.SpringBootTest;

@DataNeo4jTest
class SpringBootNeo4jApplicationTests {

    @Autowired
    private CityRepository cityRepository;

    @Test
    void findOneByNameAndState() {
        City city = cityRepository.findOneByNameAndState("rock", "1").orElse(null);
        System.out.println(city);

        System.out.println("==============================================");
        city = cityRepository.findOneByNameAndState("rock", "1").orElse(null);
        System.out.println(city);
    }

    @Test
    void save() {
        City city = new City("rock", "1");
        city = cityRepository.save(city);
        System.out.println(city);

//        System.out.println("--------------------------------------");
//        Iterable<City> cityIterable = cityRepository.findAll();
//        cityIterable.forEach(city1 -> System.out.println(city1));
//
//        System.out.println("--------------------------------------");
//        city = cityRepository.findById(31L).orElse(null);
//        System.out.println(city);
    }
}
