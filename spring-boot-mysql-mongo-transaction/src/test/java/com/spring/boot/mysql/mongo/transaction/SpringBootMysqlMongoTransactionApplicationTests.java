package com.spring.boot.mysql.mongo.transaction;

import com.spring.boot.mysql.mongo.transaction.service.AggregateService;
import com.spring.boot.mysql.mongo.transaction.service.MysqlService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;

@SpringBootTest
class SpringBootMysqlMongoTransactionApplicationTests {

    @Autowired
    private MysqlService mysqlService;

    @Autowired
    private AggregateService aggregateService;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    void test() {
        mongoTemplate.dropCollection("artanis");
        mongoTemplate.createCollection("artanis");
    }

    @Test
    void contextLoads() throws Exception {
        mysqlService.saveMysqlRecord();
    }

    @Test
    void aggregate() throws Exception {
        aggregateService.aggregate();
    }

}
