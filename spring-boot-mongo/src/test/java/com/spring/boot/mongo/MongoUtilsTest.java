package com.spring.boot.mongo;

import com.mongodb.bulk.BulkWriteResult;
import com.spring.boot.mongo.entity.User;
import com.spring.boot.mongo.utils.MongoUtils;
import org.bson.Document;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

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
    public void insert() {
        String collectionName = "user_collection";

        User user = new User("userId_000001", "userName_000001", "1234567890", null);
        user = mongoUtils.insert(user, collectionName);
        System.out.println("inserted user: " + user);
    }

    @Test
    public void find() {
        String collectionName = "user_collection";

        Criteria criteria = Criteria.where("userId").is("userId_000001").and("userName").is("userName_000001").and("password").is("1234567890");
        Query query = new Query(criteria);
        List<User> userList = mongoUtils.find(query, User.class, collectionName);
        System.out.println("userList: " + userList);
    }

    @Test
    public void insertBasicDBObject() {
        String collectionName = "user_collection";

        Document document = new Document();
        document.put("userId", "userId_000002");
        document.put("userName", "userName_000002");
        document.put("password", "1234567890");
        document = mongoUtils.insert(document, collectionName);
        System.out.println("inserted document: " + document);
    }

    @Test
    public void findBasicDBObject() {
        String collectionName = "user_collection";

        Criteria criteria = Criteria.where("userId").is("userId_000002").and("userName").is("userName_000002").and("password").is("1234567890");
        Query query = new Query(criteria);
        List<User> userList = mongoUtils.find(query, User.class, collectionName);
        System.out.println("userList: " + userList);

        List<Document> documentList = mongoUtils.find(query, Document.class, collectionName);
        System.out.println("documentList: " + documentList);
        if (!CollectionUtils.isEmpty(documentList)) {
            System.out.println("-------------------");
            documentList.forEach(System.out::println);
        }
    }

    @Test
    public void bulkInsert() {
        String collectionName = "user_collection";

        List<User> userList = new ArrayList<>();
        userList.add(new User("userId_000003", "userName_000003", "1234567890", null));
        userList.add(new User("userId_000004", "userName_000004", "1234567890", null));
        userList.add(new User("userId_000005", "userName_000005", "1234567890", null));

        // 返回批量操作写入结果，
//        BulkWriteResult writeResult = mongoUtils.bulkInsert(userList, collectionName);

        // 返回批量新增对象，每个对象都包含新增id
        userList = (List<User>) mongoUtils.insert(userList, collectionName);

        System.out.println();
    }
}
