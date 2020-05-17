package com.spring.boot.elasticsearch.repository;

import com.spring.boot.elasticsearch.entity.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * <p>Description: </p>
 *
 * @author rock.jiang
 * Date 2020/05/17 23:10
 */
public interface PersonRepository extends ElasticsearchRepository<Person, String> {
    /**
     * ?0: 参数索引，从0开始
     *
     * @param firstName
     * @param pageable
     * @return
     */
    @Query("{\"match\": {\"firstName\": {\"query\": \"?0\"}}}")
    Page<Person> findByFirstName(String firstName, Pageable pageable);

    /**
     * org.springframework.data.elasticsearch.UncategorizedElasticsearchException:
     * Elasticsearch exception [type=parsing_exception, reason=[match] query doesn't support multiple fields,
     * found [firstName] and [lastName]]; nested exception is ElasticsearchStatusException[Elasticsearch exception
     * [type=parsing_exception, reason=[match] query doesn't support multiple fields, found [firstName] and [lastName]]]
     *
     * @param firstName
     * @param lastName
     * @param pageable
     * @return
     */
    @Query("{\"match\": {\"firstName\": {\"query\": \"?0\"}, \"lastName\": {\"query\": \"?1\"}}}")
    Page<Person> findByName(String firstName, String lastName, Pageable pageable);
}
