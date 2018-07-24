package com.spring.boot.mongo.util;

import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

/**
 * <p>Description: Mongo工具类</p>
 *
 * @author Rock Jiang
 * @version 1.0
 * @date 2018/5/21 0021
 */
@Component
public class MongoUtil {
    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 单个查询：根据条件查询
     *
     * @param criteria
     * @param collectionName
     * @return
     */
    public Document findOneByColumns(Criteria criteria, String collectionName){
        return mongoTemplate.findOne(new Query(criteria), Document.class, collectionName);
    }

    /**
     * 单个查询：根据ID查询
     *
     * @param objectId
     * @param collectionName
     * @return
     */
    public Document findOneById(ObjectId objectId, String collectionName){
        return mongoTemplate.findById(objectId, Document.class, collectionName);
    }

    /**
     * 单个查询：根据正则查询
     *
     * @param objectId
     * @param collectionName
     * @return
     */
    public Document findOneByRegexp(ObjectId objectId, String collectionName){
        return null;
    }

    /**
     * List查询：根据条件查询
     *
     * @param criteria
     * @param collectionName
     * @return
     */
    public List<Document> findListByColumns(Criteria criteria, String collectionName){
        return mongoTemplate.find(new Query(criteria), Document.class, collectionName);
    }

    /**
     * 新增：单个对象
     *
     * @param object
     * @param collectionName
     * @return
     */
    public void insertObject(Object object, String collectionName){
        mongoTemplate.insert(object, collectionName);
    }

    /**
     * 新增：Collection
     *
     * @param collection
     * @param collectionName
     */
    public void insertCollection(Collection<? extends Object> collection, String collectionName) {
        mongoTemplate.insert(collection, collectionName);
    }

    /**
     * 更新：使用update更新query查询首条结果
     *
     * @param query
     * @param update
     * @param collectionName
     * @return
     */
    public long updateFirst(Query query, Update update, String collectionName) {
        UpdateResult updateResult = mongoTemplate.updateFirst(query, update, collectionName);
        if (updateResult.wasAcknowledged() == true) {
            //更新成功时返回更新记录数
            return updateResult.getModifiedCount();
        }

        return 0;
    }

    /**
     * 更新：使用update更新query查询结果
     *
     * @param query
     * @param update
     * @param collectionName
     * @return
     */
    public long updateMulti(Query query, Update update, String collectionName) {
        UpdateResult updateResult = mongoTemplate.updateMulti(query, update, collectionName);
        if (updateResult.wasAcknowledged() == true) {
            //更新成功时返回更新记录数
            return updateResult.getModifiedCount();
        }

        return 0;
    }

    /**
     * 更新或新增：query结果存在则使用update更新，否则新增
     *
     * @param query
     * @param update
     * @param collectionName
     * @return
     */
    public long upsert(Query query, Update update, String collectionName) {
        UpdateResult updateResult = mongoTemplate.upsert(query, update, collectionName);
        if (updateResult.wasAcknowledged() == true) {
            //更新成功时返回更新记录数
            return updateResult.getModifiedCount();
        }

        return 0;
    }

    /**
     * 删除：根据ID删除
     *
     * @param objectId
     * @param collectionName
     * @return
     */
    public long remove(ObjectId objectId, String collectionName) {
        DeleteResult deleteResult = mongoTemplate.remove(objectId, collectionName);
        if (deleteResult.wasAcknowledged() == true) {
            //更新成功时返回更新记录数
            return deleteResult.getDeletedCount();
        }

        return 0;
    }

    /**
     * 删除：删除query结果
     *
     * @param query
     * @param collectionName
     * @return
     */
    public long remove(Query query, String collectionName) {
        DeleteResult deleteResult = mongoTemplate.remove(query, collectionName);
        if (deleteResult.wasAcknowledged() == true) {
            //更新成功时返回更新记录数
            return deleteResult.getDeletedCount();
        }

        return 0;
    }
}
