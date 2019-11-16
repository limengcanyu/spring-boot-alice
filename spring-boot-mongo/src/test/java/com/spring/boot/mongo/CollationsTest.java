package com.spring.boot.mongo;

import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Collation allows users to specify language-specific rules for string comparison, such as rules for lettercase and accent marks.
 *
 * You can specify collation for a collection or a view, an index, or specific operations that support collation.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CollationsTest {
    private static final Logger logger = LoggerFactory.getLogger(CollationsTest.class);

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private MongoOperations mongoOps;

}
