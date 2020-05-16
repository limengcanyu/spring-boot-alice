package com.spring.boot.elasticsearch;

import com.alibaba.fastjson.JSONObject;
import com.spring.boot.elasticsearch.entity.Person;
import com.spring.boot.elasticsearch.utils.LocalDateTimeUtils;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.index.query.QueryBuilders;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.query.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * <p>Description: </p>
 *
 * @author rock
 * date 2019/06/23
 */
@Slf4j
@SpringBootTest
public class ElasticsearchTemplateTest {

    @Autowired
    private ElasticsearchRestTemplate elasticsearchRestTemplate;

    @Test
    public void save() {
        Person person = new Person();
//        person.setId("cb7bef");
        person.setFirstName("artanis");
        person.setLastName("xxx");
        person.setCreateTime(LocalDateTimeUtils.format(LocalDateTime.now()));

        IndexQuery indexQuery = new IndexQueryBuilder()
//                .withId(person.getId())
                .withObject(person)
                .build();
        String documentId = elasticsearchRestTemplate.index(indexQuery);
        log.debug("documentId: {}", documentId);


        person = new Person();
//        person.setId("cb7bef");
        person.setFirstName("samuro");
        person.setLastName("yyy");
        person.setCreateTime(LocalDateTimeUtils.format(LocalDateTime.now()));

        indexQuery = new IndexQueryBuilder()
//                .withId(person.getId())
                .withObject(person)
                .build();
        documentId = elasticsearchRestTemplate.index(indexQuery);
        log.debug("documentId: {}", documentId);


        person = new Person();
//        person.setId("cb7bef");
        person.setFirstName("rock");
        person.setLastName("Connor");
        person.setCreateTime(LocalDateTimeUtils.format(LocalDateTime.now()));

        indexQuery = new IndexQueryBuilder()
//                .withId(person.getId())
                .withObject(person)
                .build();
        documentId = elasticsearchRestTemplate.index(indexQuery);
        log.debug("documentId: {}", documentId);

    }

    @Test
    public void getQuery() {
        Person person = elasticsearchRestTemplate.queryForObject(GetQuery.getById("5VSfG3IB7wFlGJhGS-vU"), Person.class);
        log.debug("person: {}", person);

        person = elasticsearchRestTemplate.queryForObject(GetQuery.getById("5lSfG3IB7wFlGJhGTOtP"), Person.class);
        log.debug("person: {}", person);

        person = elasticsearchRestTemplate.queryForObject(GetQuery.getById("51SfG3IB7wFlGJhGTOte"), Person.class);
        log.debug("person: {}", person);
    }

    @Test
    public void criteriaQuery() {
        Criteria criteria = Criteria.where("firstName").is("artanis");
        Person person = elasticsearchRestTemplate.queryForObject(new CriteriaQuery(criteria), Person.class);
        log.debug("person: {}", person);

//        criteria = Criteria.where("firstName").is("samuro");
//        person = elasticsearchRestTemplate.queryForObject(new CriteriaQuery(criteria), Person.class);
//        log.debug("person: {}", person);
//
//        criteria = Criteria.where("firstName").is("rock");
//        person = elasticsearchRestTemplate.queryForObject(new CriteriaQuery(criteria), Person.class);
//        log.debug("person: {}", person);
    }

    /**
     * java.lang.NoSuchMethodError: 'long org.elasticsearch.search.SearchHits.getTotalHits()'
     */
    @Test
    public void stringQuery() {
        Person person = elasticsearchRestTemplate.queryForObject(
                new StringQuery(""),
                Person.class);
        log.debug("person: {}", person);

//        criteria = Criteria.where("firstName").is("samuro");
//        person = elasticsearchRestTemplate.queryForObject(new CriteriaQuery(criteria), Person.class);
//        log.debug("person: {}", person);
//
//        criteria = Criteria.where("firstName").is("rock");
//        person = elasticsearchRestTemplate.queryForObject(new CriteriaQuery(criteria), Person.class);
//        log.debug("person: {}", person);
    }

    /**
     * java.lang.NoSuchMethodError: 'long org.elasticsearch.search.SearchHits.getTotalHits()'
     */
    @Test
    public void searchQuery() {
        SearchQuery query = new NativeSearchQueryBuilder()
                .withQuery(QueryBuilders.matchQuery("firstName", "samuro"))
                .withPageable(PageRequest.of(0, 10))
                .build();
        List<Person> personList = elasticsearchRestTemplate.queryForList(query, Person.class);
        log.debug("personList: {}", JSONObject.toJSONString(personList));

    }
}
