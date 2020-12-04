package com.spring.boot.mysql.mongo.transaction.service.impl;

import com.spring.boot.mysql.mongo.transaction.annotation.MultiTransactional;
import com.spring.boot.mysql.mongo.transaction.dao.entity.PlatformSalaryItem;
import com.spring.boot.mysql.mongo.transaction.service.AggregateService;
import com.spring.boot.mysql.mongo.transaction.service.IPlatformSalaryItemService;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

/**
 * <p>Description: </p>
 *
 * @author rock.jxf
 * @date 2020/12/5 0:47
 */
@Slf4j
@Service
public class AggregateServiceImpl implements AggregateService {

    @Autowired
    private IPlatformSalaryItemService platformSalaryItemService;

    @Autowired
    private MongoTemplate mongoTemplate;

    @MultiTransactional
    @Override
    public String aggregate() throws Exception {
        // put your business logic here
        PlatformSalaryItem item = new PlatformSalaryItem();
        item.setItemCode("item_134");
        item.setItemName("item_134");
        platformSalaryItemService.save(item);

        Document employee = new Document();
        employee.put("tenant_id", "tenant_000001");
        employee.put("company_id", "company_000001");
        employee.put("salary_month", "2019-01");
        employee.put("employee_id", "employee_000001");
        employee.put("employee_name", "王一");
        employee.put("department_id", "department_000001");
        employee.put("department_name", "部门000001");

        Document iDoc = mongoTemplate.insert(employee, "artanis");
        log.debug("iDoc: {}", iDoc);

//        throw new Exception();
        return null;
    }
}
