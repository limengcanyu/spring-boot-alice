package com.spring.boot.elasticsearch.service.impl;

import com.spring.boot.elasticsearch.entity.Person;
import com.spring.boot.elasticsearch.repository.PersonRepository;
import com.spring.boot.elasticsearch.service.PersonService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * <p>Description: </p>
 *
 * @author rock.jiang
 * Date 2020/05/17 23:15
 */
@Slf4j
@AllArgsConstructor
@Service
public class PersonServiceImpl implements PersonService {
    private final PersonRepository personRepository;

    @Override
    public Page<Person> findByFirstName(String firstName, Pageable pageable) {
        return personRepository.findByFirstName(firstName, pageable);
    }

    @Override
    public Page<Person> findByFirstNameAndLastName(String firstName, String lastName, Pageable pageable) {
        return personRepository.findByFirstNameAndLastName(firstName, lastName, pageable);
    }
}
