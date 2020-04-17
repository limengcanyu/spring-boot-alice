package com.spring.boot.mongo;

import com.spring.boot.mongo.service.TransactionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * <p>Description: </p>
 *
 * @author rock.jiang
 * Date 2020/04/11 12:04
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TransactionServiceTest {
    @Autowired
    private TransactionService transactionService;
    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    public void test() {
        mongoTemplate.dropCollection("artanis");
        mongoTemplate.createCollection("artanis");

        try {
            transactionService.transactionMethod1();
//            transactionService.transactionMethod2();
        } catch (Exception e) {
            e.printStackTrace();
        }

        transactionService.select();
    }
}
