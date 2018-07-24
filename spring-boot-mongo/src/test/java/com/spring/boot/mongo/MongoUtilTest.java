package com.spring.boot.mongo;

import com.spring.boot.mongo.dao.entity.PayrollCalcResult;
import com.spring.boot.mongo.util.MongoUtil;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * <p>Description: </p>
 *
 * @author Rock Jiang
 * @version 1.0
 * @date 2018/5/21 0021
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MongoApplication.class)
public class MongoUtilTest {
    @Autowired
    private MongoUtil mongoUtil;

    @Test
    public void findOneByColumns() {
        Document document = mongoUtil.findOneByColumns(Criteria.where("batch_id").is("GL000007_201801_0000000179"), "fc_payroll_calc_result_table");
        System.out.println("-----");
    }

    @Test
    public void findOneById() {
        Document document = mongoUtil.findOneById(new ObjectId("5adaa1f7e445d504cb54cbe5"), "fc_payroll_calc_result_table");
        System.out.println("-----");
    }

    @Test
    public void insertObject() {
        PayrollCalcResult payrollCalcResult = new PayrollCalcResult();
        payrollCalcResult.setBatch_id("2123213213213");
        mongoUtil.insertObject(payrollCalcResult, "fc_payroll_calc_result_table");
    }
}
