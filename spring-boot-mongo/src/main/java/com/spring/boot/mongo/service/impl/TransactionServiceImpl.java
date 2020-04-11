package com.spring.boot.mongo.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.spring.boot.mongo.service.TransactionService;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>Description: </p>
 *
 * @author rock.jiang
 * Date 2020/04/11 13:36
 */
@Service
public class TransactionServiceImpl implements TransactionService {
    @Autowired
    private MongoTemplate mongoTemplate;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void insert() throws Exception {
        Document employee = new Document();
        employee.put("tenant_id", "tenant_000001");
        employee.put("company_id", "company_000001");
        employee.put("salary_month", "2019-01");
        employee.put("employee_id", "employee_000001");
        employee.put("employee_name", "王一");
        employee.put("department_id", "department_000001");
        employee.put("department_name", "部门000001");

        mongoTemplate.dropCollection("artanis");

        mongoTemplate.insert(employee, "artanis");

        throw new Exception();
    }

    @Override
    public void select() {
        List<Document> documentList = mongoTemplate.findAll(Document.class, "artanis");
        System.out.println("documentList: " + JSONObject.toJSONString(documentList));
    }
}
