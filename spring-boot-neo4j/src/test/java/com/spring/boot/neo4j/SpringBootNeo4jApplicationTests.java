package com.spring.boot.neo4j;

import com.alibaba.fastjson.JSONObject;
import com.spring.boot.neo4j.entity.City;
import com.spring.boot.neo4j.entity.Movie;
import com.spring.boot.neo4j.entity.Person;
import com.spring.boot.neo4j.repository.CityRepository;
import com.spring.boot.neo4j.repository.MovieRepository;
import com.spring.boot.neo4j.repository.PersonRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringBootNeo4jApplicationTests {

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private PersonRepository personRepository;

    @Test
    void saveMovie() {
        movieRepository.deleteAll();
        personRepository.deleteAll();

        Movie m1 = new Movie("无问西东", "2018");
        Movie m2 = new Movie("罗曼蒂克消亡史", "2016");
        movieRepository.save(m1);
        movieRepository.save(m2);
    }

    @Test
    void savePerson() {
        Person p1 = new Person("章子怡", "1979");
        Person p2 = new Person("李芳芳", "1976");
        Person p3 = new Person("程耳", "1970");

        Movie m1 = movieRepository.findByTitle("罗曼蒂克消亡史");
        Movie m2 = movieRepository.findByTitle("无问西东");
        System.out.println("m1: " + m1);
        System.out.println("m2: " + m2);

        if (m1!=null) {
            p1.addActor(m1);
            p3.addDirector(m1);
        }
        if (m2!=null) {
            p1.addActor(m2);
            p2.addDirector(m2);
        }

        personRepository.save(p1);
        personRepository.save(p2);
        personRepository.save(p3);
    }

    @Test
    public void findByTitle() {
        Movie movie = movieRepository.findByTitle("罗曼蒂克消亡史");
        System.out.println(JSONObject.toJSONString(movie));

    }

    @Test
    public void findByName() {
        Person person = personRepository.findByName("章子怡");
        System.out.println(JSONObject.toJSONString(person));
    }

    @Test
    void saveCity() {
        City city;

//        city = new City("hangzhou", "5");
//        city = cityRepository.save(city);
//        System.out.println(city);

        System.out.println("--------------------------------------");
        Iterable<City> cityIterable = cityRepository.findAll();
        cityIterable.forEach(System.out::println);

        System.out.println("--------------------------------------");
        city = cityRepository.findById(25L).orElse(null);
        System.out.println(city);
    }

    @Test
    void findOneCityByNameAndState() {
        City city = cityRepository.findOneByNameAndState("shanghai", "1");
        System.out.println(city);

        System.out.println("==============================================");
        city = cityRepository.findOneByNameAndState("nanyang", "2");
        System.out.println(city);
    }

}
