package com.spring.boot.mongo;

import com.alibaba.fastjson.JSONObject;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.CollectionUtils;

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
@SpringBootTest
public class MongoTemplateTest {
    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    public void collectionExists() {
        boolean isExist = mongoTemplate.collectionExists("sample_table");
        System.out.println(isExist ? "collection is exist!" : "collection is not exist!");
    }

    @Test
    public void insertDocument() {
        Document document = new Document();
        document.put("company_id", "company_00001");
        document.put("employee_id", "employee_00001");
        document.put("employee_name", "employee_00001");

        mongoTemplate.insert(document, "employee_table");

        document = new Document();
        document.put("company_id", "company_00001");
        document.put("employee_id", "employee_00002");
        document.put("employee_name", "employee_00002");

        mongoTemplate.insert(document, "employee_table");
    }

    @Test
    public void findOneDocument() {
        String company_id = "company_00001";
        String employee_id = "employee_00001";

        Query query = new Query(Criteria.where("company_id").is(company_id).and("employee_id").is(employee_id));
        Document document = mongoTemplate.findOne(query, Document.class, "employee_table");
        System.out.println(document);
    }

    @Test
    public void updateDocument() {
        String company_id = "company_00001";
        String employee_id = "employee_00001";

        Query query = new Query(Criteria.where("company_id").is(company_id).and("employee_id").is(employee_id));
        Update update = new Update();
        update.set("employee_name", "古陵逝烟");
        update.set("employee_age", 12); // 表中无此字段时新增此字段

        mongoTemplate.upsert(query, update, Document.class, "employee_table");

        Document document = mongoTemplate.findOne(query, Document.class, "employee_table");
        System.out.println(document);
    }

    /**
     * 根据条件查询，返回Document对象，即Map对象
     */
    @Test
    public void find() {
        String company_id = "company_00001";
        Query query = new Query(Criteria.where("company_id").is(company_id));
        query.fields().include("employee_id"); // 查询指定字段
        List<Document> documentList = mongoTemplate.find(query, Document.class, "employee_table");
        documentList.forEach(document -> System.out.println(JSONObject.toJSONString(document)));
    }

    /**
     * 根据ID查询
     */
    @Test
    public void findById() {
        ObjectId objectId = new ObjectId("5d73b69513fd245af8a4f5ec");
        Document document = mongoTemplate.findById(objectId, Document.class, "employee_table");
        System.out.println(JSONObject.toJSONString(document));
    }

    /**
     * 查询MongoDB所有Collection
     */
    @Test
    public void getCollectionNames() {
        Set<String> collectionNames = mongoTemplate.getCollectionNames();
        if (!CollectionUtils.isEmpty(collectionNames)) {
            collectionNames.forEach(collectionName -> System.out.println("collectionName: " + collectionName));
        }
    }


}
