package com.spring.boot.mongo;

import com.spring.boot.mongo.utils.DocumentUtils;
import com.spring.boot.mongo.utils.MongoUtils;
import org.bson.Document;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.mapreduce.GroupBy;
import org.springframework.data.mongodb.core.mapreduce.GroupByResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

/**
 * <p>Description: </p>
 *
 * @author Rock Jiang
 * @version 1.0
 * @date 2018/5/21 0021
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MongoUtilsTest {
    @Autowired
    private MongoUtils mongoUtils;

    @Test
    public void group() {
        String tenantId = "tenant_000001";
        String companyId = "company_000001";
        String salaryMonth = "2019-12";
        String minus11Month = "2018-12";
        Set<String> employeeIdSet = new HashSet<>();
        employeeIdSet.add("employee_000002");
        employeeIdSet.add("employee_000003");
        employeeIdSet.add("employee_000004");

        Criteria criteria = Criteria.where("tenant_id").is(tenantId).and("company_id").is(companyId)
                .and("salary_month").gte(minus11Month).lte(salaryMonth)
                .and("employee_id").in(employeeIdSet)
                .and("item_020").gt(0.00);

        GroupBy groupBy = GroupBy.key("employee_id").initialDocument("{ count: 0 }").reduceFunction("function(doc, prev) { prev.count += 1 }");

        GroupByResults<Document> results = mongoUtils.group(criteria, "salary_template_data", groupBy, Document.class);

        Map<String, Integer> map = new HashMap<>();
        for (Document result : results) {
            String employeeId = DocumentUtils.getFieldString(result, "employee_id");
            double count = DocumentUtils.getFieldDouble(result, "count");

            map.put(employeeId, (int) count);
        }

        System.out.println(map);
    }

    @Test
    public void distinct() {
        String tenantId = "tenant_000001";
        String companyId = "company_000001";
        String salaryMonth = "2020-01";

        Query query = Query.query(
                Criteria.where("tenant_id").is(tenantId).and("company_id").is(companyId).and("salary_month").is(salaryMonth)
        );

        // 根据Query对salary_batch字段distinct
        List<Integer> salaryBatchList = mongoUtils.findDistinct(query, "salary_batch", "salary_template_data", Document.class, Integer.class);
        System.out.println(salaryBatchList);
    }
}
