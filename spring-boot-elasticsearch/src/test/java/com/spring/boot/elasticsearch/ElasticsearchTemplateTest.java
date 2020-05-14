package com.spring.boot.elasticsearch;

import com.spring.boot.elasticsearch.entity.Person;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.query.GetQuery;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;

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
    public void save(){
        Person person = new Person();
        person.setId("cb7bef");
        person.setFirstName("Sarah");
        person.setLastName("Connor");

        IndexQuery indexQuery = new IndexQueryBuilder()
                .withId(person.getId())
                .withObject(person)
                .build();
        String documentId = elasticsearchRestTemplate.index(indexQuery);
        log.debug("documentId: {}", documentId);
    }

    @Test
    public void findById() {
        Person person = elasticsearchRestTemplate
                .queryForObject(GetQuery.getById("cb7bef".toString()), Person.class);
        log.debug("person: {}", person);
    }
}
