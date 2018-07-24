package com.spring.boot.mongo;

import com.alibaba.fastjson.JSONObject;
import com.spring.boot.mongo.dao.entity.PayrollCalcResult;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Set;

/**
 * <p>Description: </p>
 *
 * @author Rock Jiang
 * @version 1.0
 * @date 2018/5/21 0021
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MongoApplication.class)
public class MongoTemplateTest {
    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    public void collectionExists() {
        boolean isExist = mongoTemplate.collectionExists("fc_payroll_calc_result_table");
        System.out.println(isExist == true ? "collection is exist!" : "collection is not exist!");
    }

    /**
     * 根据条件查询，返回Document对象，即Map对象
     */
    @Test
    public void find() {
        String batchCode = "GL000007_201801_0000000179";
        int grantType = 1;
        Query query = new Query(Criteria.where("batch_id").is(batchCode).and("batch_type").is(grantType));
        List<Document> dbObjectList = mongoTemplate.find(query, Document.class, "fc_payroll_calc_result_table");
        System.out.println("dbObjectList： " + JSONObject.toJSONString(dbObjectList));
        if (!CollectionUtils.isEmpty(dbObjectList)) {
            dbObjectList.stream().forEach(dbObject ->
                    System.out.println("batch_id: " + dbObject.get("batch_id") + " income_year_month: " + dbObject.get("income_year_month") + " emp_id: " + dbObject.get("emp_id"))
            );
        }
    }

    /**
     * 根据条件查询指定字段，返回Document对象，即Map对象
     */
    @Test
    public void findFields() {
        String batchCode = "GL000007_201801_0000000179";
        int grantType = 1;
        Query query = new Query(Criteria.where("batch_id").is(batchCode).and("batch_type").is(grantType));
        //查询指定字段
        query.fields().include("batch_id");
        List<Document> dbObjectList = mongoTemplate.find(query, Document.class, "fc_payroll_calc_result_table");
        System.out.println("dbObjectList： " + JSONObject.toJSONString(dbObjectList));
        if (!CollectionUtils.isEmpty(dbObjectList)) {
            dbObjectList.stream().forEach(dbObject ->
                    System.out.println("batch_id: " + dbObject.get("batch_id") + " income_year_month: " + dbObject.get("income_year_month") + " emp_id: " + dbObject.get("emp_id"))
            );
        }
    }

    /**
     * 根据条件查询，返回封装实体对象
     */
    @Test
    public void find2() {
        String batchCode = "GL000007_201801_0000000179";
        int grantType = 1;
        Query query = new Query(Criteria.where("batch_id").is(batchCode).and("batch_type").is(grantType));
        List<PayrollCalcResult> payrollCalcResultList = mongoTemplate.find(query, PayrollCalcResult.class, "fc_payroll_calc_result_table");
        System.out.println("dbObjectList： " + JSONObject.toJSONString(payrollCalcResultList));
        if (!CollectionUtils.isEmpty(payrollCalcResultList)) {
            payrollCalcResultList.stream().forEach(payrollCalcResult ->
                    System.out.println(payrollCalcResult)
            );
        }
    }

    /**
     * 根据ID查询
     */
    @Test
    public void findById() {
        ObjectId objectId = new ObjectId("5af548ad159aa35f780f2b1f");
        Document document = mongoTemplate.findById(objectId, Document.class, "fc_payroll_calc_result_table");
        if (!ObjectUtils.isEmpty(document)) {
            System.out.println("batch_id: " + document.get("batch_id") + " emp_id: " + document.get("emp_id") + " emp_name: " + document.get("emp_name"));
        }
    }

    /**
     * 根据条件查询，返回单个对象
     */
    @Test
    public void findOne() {
        String batchCode = "GL000007_201801_0000000179";
        int grantType = 1;
        Query query = new Query(Criteria.where("batch_id").is(batchCode).and("batch_type").is(grantType));
        Document document = mongoTemplate.findOne(query, Document.class, "fc_payroll_calc_result_table");
        if (!ObjectUtils.isEmpty(document)) {
            System.out.println("batch_id: " + document.get("batch_id") + " emp_id: " + document.get("emp_id") + " emp_name: " + document.get("emp_name"));
        }
    }

    /**
     * 查询MongoDB所有Collection
     */
    @Test
    public void getCollectionNames() {
        Set<String> collectionNames = mongoTemplate.getCollectionNames();
        if (!CollectionUtils.isEmpty(collectionNames)) {
            collectionNames.stream().forEach(collectionName -> System.out.println("collectionName: " + collectionName));
        }
    }
}
