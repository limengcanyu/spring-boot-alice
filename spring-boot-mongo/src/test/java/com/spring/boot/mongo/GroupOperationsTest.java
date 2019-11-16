package com.spring.boot.mongo;

import com.spring.boot.mongo.entity.XObject;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.mapreduce.GroupBy;
import org.springframework.data.mongodb.core.mapreduce.GroupByResults;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GroupOperationsTest {
    private static final Logger logger = LoggerFactory.getLogger(GroupOperationsTest.class);

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private MongoOperations mongoOps;

    @Test
    public void query() {
        mongoTemplate.dropCollection("group_test_collection");

        Document document = new Document();
        ObjectId id = new ObjectId("4ec1d25d41421e2015da64f1");
        document.put("_id", id);
        document.put("x", 1);
        mongoTemplate.save(document, "group_test_collection");

        document = new Document();
        id = new ObjectId("4ec1d25d41421e2015da64f2");
        document.put("_id", id);
        document.put("x", 1);
        mongoTemplate.save(document, "group_test_collection");

        document = new Document();
        id = new ObjectId("4ec1d25d41421e2015da64f3");
        document.put("_id", id);
        document.put("x", 2);
        mongoTemplate.save(document, "group_test_collection");

        document = new Document();
        id = new ObjectId("4ec1d25d41421e2015da64f4");
        document.put("_id", id);
        document.put("x", 3);
        mongoTemplate.save(document, "group_test_collection");

        document = new Document();
        id = new ObjectId("4ec1d25d41421e2015da64f5");
        document.put("_id", id);
        document.put("x", 3);
        mongoTemplate.save(document, "group_test_collection");

        document = new Document();
        id = new ObjectId("4ec1d25d41421e2015da64f6");
        document.put("_id", id);
        document.put("x", 3);
        mongoTemplate.save(document, "group_test_collection");

        GroupByResults<XObject> results = mongoTemplate.group("group_test_collection",
                GroupBy.key("x").initialDocument("{ count: 0 }").reduceFunction("function(doc, prev) { prev.count += 1 }"),
                XObject.class);
        System.out.println();
    }
}
