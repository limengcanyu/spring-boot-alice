package com.spring.boot.mongo;

import org.bson.Document;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>Description: </p>
 *
 * @author rock.jiang
 * Date 2019/12/15 14:21
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MongoTemplateTest {
    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    public void createTestData() {
        List<Document> insertList = new ArrayList<>();

        Document employee = new Document();
        employee.put("tenant_id", "tenant_000001");
        employee.put("company_id", "company_000001");
        employee.put("salary_month", "2019-01");
        employee.put("employee_id", "employee_000001");
        employee.put("employee_name", "王一");
        employee.put("department_id", "department_000001");
        employee.put("department_name", "部门000001");
        insertList.add(employee);

        employee = new Document();
        employee.put("tenant_id", "tenant_000001");
        employee.put("company_id", "company_000001");
        employee.put("salary_month", "2019-01");
        employee.put("employee_id", "employee_000002");
        employee.put("employee_name", "王二");
        employee.put("department_id", "department_000002");
        employee.put("department_name", "部门000002");
        insertList.add(employee);

        employee = new Document();
        employee.put("tenant_id", "tenant_000001");
        employee.put("company_id", "company_000001");
        employee.put("salary_month", "2019-02");
        employee.put("employee_id", "employee_000001");
        employee.put("employee_name", "王一");
        employee.put("department_id", "department_000001");
        employee.put("department_name", "部门000001");
        insertList.add(employee);

        employee = new Document();
        employee.put("tenant_id", "tenant_000001");
        employee.put("company_id", "company_000001");
        employee.put("salary_month", "2019-02");
        employee.put("employee_id", "employee_000003");
        employee.put("employee_name", "王三");
        employee.put("department_id", "department_000002");
        employee.put("department_name", "部门000002");
        insertList.add(employee);

        employee = new Document();
        employee.put("tenant_id", "tenant_000001");
        employee.put("company_id", "company_000001");
        employee.put("salary_month", "2019-03");
        employee.put("employee_id", "employee_000001");
        employee.put("employee_name", "王一");
        employee.put("department_id", "department_000001");
        employee.put("department_name", "部门01");
        insertList.add(employee);

        mongoTemplate.insert(insertList, "mongo_template_test");

    }

    @Test
    public void querySomeFields() {
        String tenant_id = "tenant_000001";
        String company_id = "company_000001";
        String salary_month_start = "2019-01";
        String salary_month_end = "2019-12";
        Query query = Query.query(Criteria.where("tenant_id").is(tenant_id).and("company_id").is(company_id)
                .and("salary_month").gte(salary_month_start).lte(salary_month_end));
        query.fields().include("department_id").include("department_name");

        List<Document> departmentList = mongoTemplate.find(query, Document.class, "mongo_template_test");
        System.out.println();
    }
}
