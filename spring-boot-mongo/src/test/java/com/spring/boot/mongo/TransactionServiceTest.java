package com.spring.boot.mongo;

import com.spring.boot.mongo.service.TransactionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
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

    @Test
    public void test() {
        try {
            transactionService.insert();
        } catch (Exception e) {
            e.printStackTrace();
        }

        transactionService.select();
    }
}
