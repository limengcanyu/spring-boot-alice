package com.spring.boot.mongo.utils;

import com.mongodb.Cursor;
import com.mongodb.bulk.BulkWriteResult;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.GeoResults;
import org.springframework.data.mongodb.core.BulkOperations;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.FindAndReplaceOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOptions;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.TypedAggregation;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.mapreduce.GroupBy;
import org.springframework.data.mongodb.core.mapreduce.GroupByResults;
import org.springframework.data.mongodb.core.mapreduce.MapReduceOptions;
import org.springframework.data.mongodb.core.mapreduce.MapReduceResults;
import org.springframework.data.mongodb.core.query.*;
import org.springframework.data.util.CloseableIterator;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.util.ClassUtils;

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
public class MongoUtils {

    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * The collection name used for the specified class by this template.
     *
     * @param entityClass must not be {@literal null}.
     * @return
     */
    String getCollectionName(Class<?> entityClass){
        return mongoTemplate.getCollectionName(entityClass);
    }

    /**
     * Query for a list of objects of type T from the specified collection.
     * <p/>
     * The object is converted from the MongoDB native representation using an instance of {@see MongoConverter}. Unless
     * configured otherwise, an instance of {@link MappingMongoConverter} will be used.
     * <p/>
     * If your collection does not contain a homogeneous collection of types, this operation will not be an efficient way
     * to map objects since the test for class type is done in the client and not on the server.
     *
     * @param entityClass the parametrized type of the returned list.
     * @param collectionName name of the collection to retrieve the objects from.
     * @return the converted collection.
     */
    public <T> List<T> findAll(Class<T> entityClass, String collectionName){
        return mongoTemplate.findAll(entityClass, collectionName);
    }

    /**
     * Execute a group operation over the entire collection. The group operation entity class should match the 'shape' of
     * the returned object that takes int account the initial document structure as well as any finalize functions.
     *
     * @param inputCollectionName the collection where the group operation will read from
     * @param groupBy the conditions under which the group operation will be performed, e.g. keys, initial document,
     *          reduce function.
     * @param entityClass The parametrized type of the returned list
     * @return The results of the group operation
     */
    public <T> GroupByResults<T> group(String inputCollectionName, GroupBy groupBy, Class<T> entityClass){
        return mongoTemplate.group(inputCollectionName, groupBy, entityClass);
    }

    /**
     * Execute a group operation restricting the rows to those which match the provided Criteria. The group operation
     * entity class should match the 'shape' of the returned object that takes int account the initial document structure
     * as well as any finalize functions.
     *
     * @param criteria The criteria that restricts the row that are considered for grouping. If not specified all rows are
     *          considered.
     * @param inputCollectionName the collection where the group operation will read from
     * @param groupBy the conditions under which the group operation will be performed, e.g. keys, initial document,
     *          reduce function.
     * @param entityClass The parametrized type of the returned list
     * @return The results of the group operation
     */
    public <T> GroupByResults<T> group(Criteria criteria, String inputCollectionName, GroupBy groupBy, Class<T> entityClass){
        return mongoTemplate.group(criteria, inputCollectionName, groupBy, entityClass);
    }

    /**
     * Execute an aggregation operation. The raw results will be mapped to the given entity class. The name of the
     * inputCollection is derived from the inputType of the aggregation.
     *
     * @param aggregation The {@link TypedAggregation} specification holding the aggregation operations, must not be
     *          {@literal null}.
     * @param collectionName The name of the input collection to use for the aggreation.
     * @param outputType The parametrized type of the returned list, must not be {@literal null}.
     * @return The results of the aggregation operation.
     * @since 1.3
     */
    public <O> AggregationResults<O> aggregate(TypedAggregation<?> aggregation, String collectionName, Class<O> outputType){
        return mongoTemplate.aggregate(aggregation, collectionName, outputType);
    }

    /**
     * Execute an aggregation operation. The raw results will be mapped to the given entity class. The name of the
     * inputCollection is derived from the inputType of the aggregation.
     *
     * @param aggregation The {@link TypedAggregation} specification holding the aggregation operations, must not be
     *          {@literal null}.
     * @param outputType The parametrized type of the returned list, must not be {@literal null}.
     * @return The results of the aggregation operation.
     * @since 1.3
     */
    public <O> AggregationResults<O> aggregate(TypedAggregation<?> aggregation, Class<O> outputType){
        return mongoTemplate.aggregate(aggregation, outputType);
    }

    /**
     * Execute an aggregation operation. The raw results will be mapped to the given entity class.
     *
     * @param aggregation The {@link Aggregation} specification holding the aggregation operations, must not be
     *          {@literal null}.
     * @param inputType the inputType where the aggregation operation will read from, must not be {@literal null} or
     *          empty.
     * @param outputType The parametrized type of the returned list, must not be {@literal null}.
     * @return The results of the aggregation operation.
     * @since 1.3
     */
    public <O> AggregationResults<O> aggregate(Aggregation aggregation, Class<?> inputType, Class<O> outputType){
        return mongoTemplate.aggregate(aggregation, inputType, outputType);
    }

    /**
     * Execute an aggregation operation. The raw results will be mapped to the given entity class.
     *
     * @param aggregation The {@link Aggregation} specification holding the aggregation operations, must not be
     *          {@literal null}.
     * @param collectionName the collection where the aggregation operation will read from, must not be {@literal null} or
     *          empty.
     * @param outputType The parametrized type of the returned list, must not be {@literal null}.
     * @return The results of the aggregation operation.
     * @since 1.3
     */
    public <O> AggregationResults<O> aggregate(Aggregation aggregation, String collectionName, Class<O> outputType){
        return mongoTemplate.aggregate(aggregation, collectionName, outputType);
    }

    /**
     * Execute an aggregation operation backed by a Mongo DB {@link Cursor}.
     * <p>
     * Returns a {@link CloseableIterator} that wraps the a Mongo DB {@link Cursor} that needs to be closed. The raw
     * results will be mapped to the given entity class. The name of the inputCollection is derived from the inputType of
     * the aggregation.
     * <p>
     * Aggregation streaming can't be used with {@link AggregationOptions#isExplain() aggregation explain}. Enabling
     * explanation mode will throw an {@link IllegalArgumentException}.
     *
     * @param aggregation The {@link TypedAggregation} specification holding the aggregation operations, must not be
     *          {@literal null}.
     * @param collectionName The name of the input collection to use for the aggreation.
     * @param outputType The parametrized type of the returned list, must not be {@literal null}.
     * @return The results of the aggregation operation.
     * @since 2.0
     */
    public <O> CloseableIterator<O> aggregateStream(TypedAggregation<?> aggregation, String collectionName, Class<O> outputType){
        return mongoTemplate.aggregateStream(aggregation, collectionName, outputType);
    }

    /**
     * Execute an aggregation operation backed by a Mongo DB {@link Cursor}.
     * <p/>
     * Returns a {@link CloseableIterator} that wraps the a Mongo DB {@link Cursor} that needs to be closed. The raw
     * results will be mapped to the given entity class and are returned as stream. The name of the inputCollection is
     * derived from the inputType of the aggregation.
     * <p/>
     * Aggregation streaming can't be used with {@link AggregationOptions#isExplain() aggregation explain}. Enabling
     * explanation mode will throw an {@link IllegalArgumentException}.
     *
     * @param aggregation The {@link TypedAggregation} specification holding the aggregation operations, must not be
     *          {@literal null}.
     * @param outputType The parametrized type of the returned list, must not be {@literal null}.
     * @return The results of the aggregation operation.
     * @since 2.0
     */
    public <O> CloseableIterator<O> aggregateStream(TypedAggregation<?> aggregation, Class<O> outputType){
        return mongoTemplate.aggregateStream(aggregation, outputType);
    }

    /**
     * Execute an aggregation operation backed by a Mongo DB {@link Cursor}.
     * <p/>
     * Returns a {@link CloseableIterator} that wraps the a Mongo DB {@link Cursor} that needs to be closed. The raw
     * results will be mapped to the given entity class.
     * <p/>
     * Aggregation streaming can't be used with {@link AggregationOptions#isExplain() aggregation explain}. Enabling
     * explanation mode will throw an {@link IllegalArgumentException}.
     *
     * @param aggregation The {@link Aggregation} specification holding the aggregation operations, must not be
     *          {@literal null}.
     * @param inputType the inputType where the aggregation operation will read from, must not be {@literal null} or
     *          empty.
     * @param outputType The parametrized type of the returned list, must not be {@literal null}.
     * @return The results of the aggregation operation.
     * @since 2.0
     */
    public <O> CloseableIterator<O> aggregateStream(Aggregation aggregation, Class<?> inputType, Class<O> outputType){
        return mongoTemplate.aggregateStream(aggregation, inputType, outputType);
    }

    /**
     * Execute an aggregation operation backed by a Mongo DB {@link Cursor}.
     * <p/>
     * Returns a {@link CloseableIterator} that wraps the a Mongo DB {@link Cursor} that needs to be closed. The raw
     * results will be mapped to the given entity class.
     * <p/>
     * Aggregation streaming can't be used with {@link AggregationOptions#isExplain() aggregation explain}. Enabling
     * explanation mode will throw an {@link IllegalArgumentException}.
     *
     * @param aggregation The {@link Aggregation} specification holding the aggregation operations, must not be
     *          {@literal null}.
     * @param collectionName the collection where the aggregation operation will read from, must not be {@literal null} or
     *          empty.
     * @param outputType The parametrized type of the returned list, must not be {@literal null}.
     * @return The results of the aggregation operation.
     * @since 2.0
     */
    public <O> CloseableIterator<O> aggregateStream(Aggregation aggregation, String collectionName, Class<O> outputType){
        return mongoTemplate.aggregateStream(aggregation, collectionName, outputType);
    }

    /**
     * Execute a map-reduce operation. The map-reduce operation will be formed with an output type of INLINE
     *
     * @param inputCollectionName the collection where the map-reduce will read from. Must not be {@literal null}.
     * @param mapFunction The JavaScript map function.
     * @param reduceFunction The JavaScript reduce function
     * @param entityClass The parametrized type of the returned list. Must not be {@literal null}.
     * @return The results of the map reduce operation
     */
    public <T> MapReduceResults<T> mapReduce(String inputCollectionName, String mapFunction, String reduceFunction, Class<T> entityClass){
        return mongoTemplate.mapReduce(inputCollectionName, mapFunction, reduceFunction, entityClass);
    }

    /**
     * Execute a map-reduce operation that takes additional map-reduce options.
     *
     * @param inputCollectionName the collection where the map-reduce will read from. Must not be {@literal null}.
     * @param mapFunction The JavaScript map function
     * @param reduceFunction The JavaScript reduce function
     * @param mapReduceOptions Options that specify detailed map-reduce behavior.
     * @param entityClass The parametrized type of the returned list. Must not be {@literal null}.
     * @return The results of the map reduce operation
     */
    public <T> MapReduceResults<T> mapReduce(String inputCollectionName, String mapFunction, String reduceFunction,
                                      MapReduceOptions mapReduceOptions, Class<T> entityClass){
        return mongoTemplate.mapReduce(inputCollectionName, mapFunction, reduceFunction, mapReduceOptions, entityClass);
    }

    /**
     * Execute a map-reduce operation that takes a query. The map-reduce operation will be formed with an output type of
     * INLINE
     *
     * @param query The query to use to select the data for the map phase. Must not be {@literal null}.
     * @param inputCollectionName the collection where the map-reduce will read from. Must not be {@literal null}.
     * @param mapFunction The JavaScript map function
     * @param reduceFunction The JavaScript reduce function
     * @param entityClass The parametrized type of the returned list. Must not be {@literal null}.
     * @return The results of the map reduce operation
     */
    public <T> MapReduceResults<T> mapReduce(Query query, String inputCollectionName, String mapFunction, String reduceFunction,
                                      Class<T> entityClass){
        return mongoTemplate.mapReduce(query, inputCollectionName, mapFunction, reduceFunction, entityClass);
    }

    /**
     * Execute a map-reduce operation that takes a query and additional map-reduce options
     *
     * @param query The query to use to select the data for the map phase. Must not be {@literal null}.
     * @param inputCollectionName the collection where the map-reduce will read from. Must not be {@literal null}.
     * @param mapFunction The JavaScript map function
     * @param reduceFunction The JavaScript reduce function
     * @param mapReduceOptions Options that specify detailed map-reduce behavior
     * @param entityClass The parametrized type of the returned list. Must not be {@literal null}.
     * @return The results of the map reduce operation
     */
    public <T> MapReduceResults<T> mapReduce(Query query, String inputCollectionName, String mapFunction, String reduceFunction,
                                      MapReduceOptions mapReduceOptions, Class<T> entityClass){
        return mongoTemplate.mapReduce(query, inputCollectionName, mapFunction, reduceFunction, mapReduceOptions, entityClass);
    }

    /**
     * Returns {@link GeoResults} for all entities matching the given {@link NearQuery}. Will consider entity mapping
     * information to determine the collection the query is ran against. Note, that MongoDB limits the number of results
     * by default. Make sure to add an explicit limit to the {@link NearQuery} if you expect a particular number of
     * results.
     *
     * @param near must not be {@literal null}.
     * @param entityClass must not be {@literal null}.
     * @return
     */
    public <T> GeoResults<T> geoNear(NearQuery near, Class<T> entityClass){
        return mongoTemplate.geoNear(near, entityClass);
    }

    /**
     * Returns {@link GeoResults} for all entities matching the given {@link NearQuery}. Note, that MongoDB limits the
     * number of results by default. Make sure to add an explicit limit to the {@link NearQuery} if you expect a
     * particular number of results.
     *
     * @param near must not be {@literal null}.
     * @param entityClass must not be {@literal null}.
     * @param collectionName the collection to trigger the query against. If no collection name is given the entity class
     *          will be inspected. Must not be {@literal null} nor empty.
     * @return
     */
    public <T> GeoResults<T> geoNear(NearQuery near, Class<T> entityClass, String collectionName){
        return mongoTemplate.geoNear(near, entityClass, collectionName);
    }

    /**
     * Map the results of an ad-hoc query on the collection for the entity class to a single instance of an object of the
     * specified type.
     * <p/>
     * The object is converted from the MongoDB native representation using an instance of {@see MongoConverter}. Unless
     * configured otherwise, an instance of {@link MappingMongoConverter} will be used.
     * <p/>
     * The query is specified as a {@link Query} which can be created either using the {@link BasicQuery} or the more
     * feature rich {@link Query}.
     *
     * @param query the query class that specifies the criteria used to find a record and also an optional fields
     *          specification.
     * @param entityClass the parametrized type of the returned list.
     * @return the converted object.
     */
    public <T> T findOne(Query query, Class<T> entityClass){
        return mongoTemplate.findOne(query, entityClass);
    }

    /**
     * Map the results of an ad-hoc query on the specified collection to a single instance of an object of the specified
     * type.
     * <p/>
     * The object is converted from the MongoDB native representation using an instance of {@see MongoConverter}. Unless
     * configured otherwise, an instance of {@link MappingMongoConverter} will be used.
     * <p/>
     * The query is specified as a {@link Query} which can be created either using the {@link BasicQuery} or the more
     * feature rich {@link Query}.
     *
     * @param query the query class that specifies the criteria used to find a record and also an optional fields
     *          specification.
     * @param entityClass the parametrized type of the returned list.
     * @param collectionName name of the collection to retrieve the objects from.
     * @return the converted object.
     */
    public <T> T findOne(Query query, Class<T> entityClass, String collectionName){
        return mongoTemplate.findOne(query, entityClass, collectionName);
    }

    /**
     * Determine result of given {@link Query} contains at least one element. <br />
     * <strong>NOTE:</strong> Any additional support for query/field mapping, etc. is not available due to the lack of
     * domain type information. Use {@link #exists(Query, Class, String)} to get full type specific support.
     *
     * @param query the {@link Query} class that specifies the criteria used to find a record.
     * @param collectionName name of the collection to check for objects.
     * @return {@literal true} if the query yields a result.
     */
    public boolean exists(Query query, String collectionName){
        return mongoTemplate.exists(query, collectionName);
    }

    /**
     * Determine result of given {@link Query} contains at least one element.
     *
     * @param query the {@link Query} class that specifies the criteria used to find a record.
     * @param entityClass the parametrized type.
     * @return {@literal true} if the query yields a result.
     */
    public boolean exists(Query query, Class<?> entityClass){
        return mongoTemplate.exists(query, entityClass);
    }

    /**
     * Determine result of given {@link Query} contains at least one element.
     *
     * @param query the {@link Query} class that specifies the criteria used to find a record.
     * @param entityClass the parametrized type. Can be {@literal null}.
     * @param collectionName name of the collection to check for objects.
     * @return {@literal true} if the query yields a result.
     */
    boolean exists(Query query, Class<?> entityClass, String collectionName){
        return mongoTemplate.exists(query, entityClass, collectionName);
    }

    /**
     * Map the results of an ad-hoc query on the collection for the entity class to a List of the specified type.
     * <p/>
     * The object is converted from the MongoDB native representation using an instance of {@see MongoConverter}. Unless
     * configured otherwise, an instance of {@link MappingMongoConverter} will be used.
     * <p/>
     * The query is specified as a {@link Query} which can be created either using the {@link BasicQuery} or the more
     * feature rich {@link Query}.
     *
     * @param query the query class that specifies the criteria used to find a record and also an optional fields
     *          specification. Must not be {@literal null}.
     * @param entityClass the parametrized type of the returned list. Must not be {@literal null}.
     * @return the List of converted objects.
     */
    public <T> List<T> find(Query query, Class<T> entityClass){
        return mongoTemplate.find(query, entityClass);
    }

    /**
     * Map the results of an ad-hoc query on the specified collection to a List of the specified type.
     * <p/>
     * The object is converted from the MongoDB native representation using an instance of {@see MongoConverter}. Unless
     * configured otherwise, an instance of {@link MappingMongoConverter} will be used.
     * <p/>
     * The query is specified as a {@link Query} which can be created either using the {@link BasicQuery} or the more
     * feature rich {@link Query}.
     *
     * @param query the query class that specifies the criteria used to find a record and also an optional fields
     *          specification. Must not be {@literal null}.
     * @param entityClass the parametrized type of the returned list. Must not be {@literal null}.
     * @param collectionName name of the collection to retrieve the objects from. Must not be {@literal null}.
     * @return the List of converted objects.
     */
    public <T> List<T> find(Query query, Class<T> entityClass, String collectionName){
        return mongoTemplate.find(query, entityClass, collectionName);
    }

    /**
     * Returns a document with the given id mapped onto the given class. The collection the query is ran against will be
     * derived from the given target class as well.
     *
     * @param id the id of the document to return. Must not be {@literal null}.
     * @param entityClass the type the document shall be converted into. Must not be {@literal null}.
     * @return the document with the given id mapped onto the given target class.
     */
    public <T> T findById(Object id, Class<T> entityClass){
        return mongoTemplate.findById(id, entityClass);
    }

    /**
     * Returns the document with the given id from the given collection mapped onto the given target class.
     *
     * @param id the id of the document to return.
     * @param entityClass the type to convert the document to.
     * @param collectionName the collection to query for the document.
     * @return he converted object or {@literal null} if document does not exist.
     */
    public <T> T findById(Object id, Class<T> entityClass, String collectionName){
        return mongoTemplate.findById(id, entityClass, collectionName);
    }

    /**
     * Finds the distinct values for a specified {@literal field} across a single {@link MongoCollection} or view and
     * returns the results in a {@link List}.
     *
     * @param field the name of the field to inspect for distinct values. Must not be {@literal null}.
     * @param entityClass the domain type used for determining the actual {@link MongoCollection}. Must not be
     *          {@literal null}.
     * @param resultClass the result type. Must not be {@literal null}.
     * @return never {@literal null}.
     * @since 2.1
     */
    public <T> List<T> findDistinct(String field, Class<?> entityClass, Class<T> resultClass) {
        return findDistinct(new Query(), field, entityClass, resultClass);
    }

    /**
     * Finds the distinct values for a specified {@literal field} across a single {@link MongoCollection} or view and
     * returns the results in a {@link List}.
     *
     * @param query filter {@link Query} to restrict search. Must not be {@literal null}.
     * @param field the name of the field to inspect for distinct values. Must not be {@literal null}.
     * @param entityClass the domain type used for determining the actual {@link MongoCollection} and mapping the
     *          {@link Query} to the domain type fields. Must not be {@literal null}.
     * @param resultClass the result type. Must not be {@literal null}.
     * @return never {@literal null}.
     * @since 2.1
     */
    public <T> List<T> findDistinct(Query query, String field, Class<?> entityClass, Class<T> resultClass){
        return mongoTemplate.findDistinct(query, field, entityClass, resultClass);
    }

    /**
     * Finds the distinct values for a specified {@literal field} across a single {@link MongoCollection} or view and
     * returns the results in a {@link List}.
     *
     * @param query filter {@link Query} to restrict search. Must not be {@literal null}.
     * @param field the name of the field to inspect for distinct values. Must not be {@literal null}.
     * @param collectionName the explicit name of the actual {@link MongoCollection}. Must not be {@literal null}.
     * @param entityClass the domain type used for mapping the {@link Query} to the domain type fields.
     * @param resultClass the result type. Must not be {@literal null}.
     * @return never {@literal null}.
     * @since 2.1
     */
    public <T> List<T> findDistinct(Query query, String field, String collectionName, Class<?> entityClass,
                             Class<T> resultClass){
        return mongoTemplate.findDistinct(query, field, collectionName, entityClass, resultClass);
    }

    /**
     * Finds the distinct values for a specified {@literal field} across a single {@link MongoCollection} or view and
     * returns the results in a {@link List}.
     *
     * @param query filter {@link Query} to restrict search. Must not be {@literal null}.
     * @param field the name of the field to inspect for distinct values. Must not be {@literal null}.
     * @param collection the explicit name of the actual {@link MongoCollection}. Must not be {@literal null}.
     * @param resultClass the result type. Must not be {@literal null}.
     * @return never {@literal null}.
     * @since 2.1
     */
    public <T> List<T> findDistinct(Query query, String field, String collection, Class<T> resultClass) {
        return findDistinct(query, field, collection, Object.class, resultClass);
    }

    /**
     * Triggers <a href="https://docs.mongodb.org/manual/reference/method/db.collection.findAndModify/">findAndModify <a/>
     * to apply provided {@link Update} on documents matching {@link Criteria} of given {@link Query}.
     *
     * @param query the {@link Query} class that specifies the {@link Criteria} used to find a record and also an optional
     *          fields specification. Must not be {@literal null}.
     * @param update the {@link Update} to apply on matching documents. Must not be {@literal null}.
     * @param entityClass the parametrized type. Must not be {@literal null}.
     * @return the converted object that was updated before it was updated or {@literal null}, if not found.
     */
    public <T> T findAndModify(Query query, Update update, Class<T> entityClass){
        return mongoTemplate.findAndModify(query, update, entityClass);
    }

    /**
     * Triggers <a href="https://docs.mongodb.org/manual/reference/method/db.collection.findAndModify/">findAndModify <a/>
     * to apply provided {@link Update} on documents matching {@link Criteria} of given {@link Query}.
     *
     * @param query the {@link Query} class that specifies the {@link Criteria} used to find a record and also an optional
     *          fields specification. Must not be {@literal null}.
     * @param update the {@link Update} to apply on matching documents. Must not be {@literal null}.
     * @param entityClass the parametrized type. Must not be {@literal null}.
     * @param collectionName the collection to query. Must not be {@literal null}.
     * @return the converted object that was updated before it was updated or {@literal null}, if not found.
     */
    public <T> T findAndModify(Query query, Update update, Class<T> entityClass, String collectionName){
        return mongoTemplate.findAndModify(query, update, entityClass, collectionName);
    }

    /**
     * Triggers <a href="https://docs.mongodb.org/manual/reference/method/db.collection.findAndModify/">findAndModify <a/>
     * to apply provided {@link Update} on documents matching {@link Criteria} of given {@link Query} taking
     * {@link FindAndModifyOptions} into account.
     *
     * @param query the {@link Query} class that specifies the {@link Criteria} used to find a record and also an optional
     *          fields specification.
     * @param update the {@link Update} to apply on matching documents.
     * @param options the {@link FindAndModifyOptions} holding additional information.
     * @param entityClass the parametrized type.
     * @return the converted object that was updated or {@literal null}, if not found. Depending on the value of
     *         {@link FindAndModifyOptions#isReturnNew()} this will either be the object as it was before the update or as
     *         it is after the update.
     */
    public <T> T findAndModify(Query query, Update update, FindAndModifyOptions options, Class<T> entityClass){
        return mongoTemplate.findAndModify(query, update, options, entityClass);
    }

    /**
     * Triggers <a href="https://docs.mongodb.org/manual/reference/method/db.collection.findAndModify/">findAndModify <a/>
     * to apply provided {@link Update} on documents matching {@link Criteria} of given {@link Query} taking
     * {@link FindAndModifyOptions} into account.
     *
     * @param query the {@link Query} class that specifies the {@link Criteria} used to find a record and also an optional
     *          fields specification. Must not be {@literal null}.
     * @param update the {@link Update} to apply on matching documents. Must not be {@literal null}.
     * @param options the {@link FindAndModifyOptions} holding additional information. Must not be {@literal null}.
     * @param entityClass the parametrized type. Must not be {@literal null}.
     * @param collectionName the collection to query. Must not be {@literal null}.
     * @return the converted object that was updated or {@literal null}, if not found. Depending on the value of
     *         {@link FindAndModifyOptions#isReturnNew()} this will either be the object as it was before the update or as
     *         it is after the update.
     */
    public <T> T findAndModify(Query query, Update update, FindAndModifyOptions options, Class<T> entityClass,
                        String collectionName){
        return mongoTemplate.findAndModify(query, update, options, entityClass, collectionName);
    }

    /**
     * Triggers
     * <a href="https://docs.mongodb.com/manual/reference/method/db.collection.findOneAndReplace/">findOneAndReplace<a/>
     * to replace a single document matching {@link Criteria} of given {@link Query} with the {@code replacement}
     * document. <br />
     * The collection name is derived from the {@literal replacement} type. <br />
     * Options are defaulted to {@link FindAndReplaceOptions#empty()}. <br />
     * <strong>NOTE:</strong> The replacement entity must not hold an {@literal id}.
     *
     * @param query the {@link Query} class that specifies the {@link Criteria} used to find a record and also an optional
     *          fields specification. Must not be {@literal null}.
     * @param replacement the replacement document. Must not be {@literal null}.
     * @return the converted object that was updated or {@literal null}, if not found.
     * @since 2.1
     */
    public <T> T findAndReplace(Query query, T replacement) {
        return findAndReplace(query, replacement, FindAndReplaceOptions.empty());
    }

    /**
     * Triggers
     * <a href="https://docs.mongodb.com/manual/reference/method/db.collection.findOneAndReplace/">findOneAndReplace<a/>
     * to replace a single document matching {@link Criteria} of given {@link Query} with the {@code replacement}
     * document.<br />
     * Options are defaulted to {@link FindAndReplaceOptions#empty()}. <br />
     * <strong>NOTE:</strong> The replacement entity must not hold an {@literal id}.
     *
     * @param query the {@link Query} class that specifies the {@link Criteria} used to find a record and also an optional
     *          fields specification. Must not be {@literal null}.
     * @param replacement the replacement document. Must not be {@literal null}.
     * @param collectionName the collection to query. Must not be {@literal null}.
     * @return the converted object that was updated or {@literal null}, if not found.
     * @since 2.1
     */
    public <T> T findAndReplace(Query query, T replacement, String collectionName) {
        return findAndReplace(query, replacement, FindAndReplaceOptions.empty(), collectionName);
    }

    /**
     * Triggers
     * <a href="https://docs.mongodb.com/manual/reference/method/db.collection.findOneAndReplace/">findOneAndReplace<a/>
     * to replace a single document matching {@link Criteria} of given {@link Query} with the {@code replacement} document
     * taking {@link FindAndReplaceOptions} into account.<br />
     * <strong>NOTE:</strong> The replacement entity must not hold an {@literal id}.
     *
     * @param query the {@link Query} class that specifies the {@link Criteria} used to find a record and also an optional
     *          fields specification. Must not be {@literal null}.
     * @param replacement the replacement document. Must not be {@literal null}.
     * @param options the {@link FindAndModifyOptions} holding additional information. Must not be {@literal null}.
     * @return the converted object that was updated or {@literal null}, if not found. Depending on the value of
     *         {@link FindAndReplaceOptions#isReturnNew()} this will either be the object as it was before the update or
     *         as it is after the update.
     * @since 2.1
     */
    public <T> T findAndReplace(Query query, T replacement, FindAndReplaceOptions options) {
        return findAndReplace(query, replacement, options, getCollectionName(ClassUtils.getUserClass(replacement)));
    }

    /**
     * Triggers
     * <a href="https://docs.mongodb.com/manual/reference/method/db.collection.findOneAndReplace/">findOneAndReplace<a/>
     * to replace a single document matching {@link Criteria} of given {@link Query} with the {@code replacement} document
     * taking {@link FindAndReplaceOptions} into account.<br />
     * <strong>NOTE:</strong> The replacement entity must not hold an {@literal id}.
     *
     * @param query the {@link Query} class that specifies the {@link Criteria} used to find a record and also an optional
     *          fields specification. Must not be {@literal null}.
     * @param replacement the replacement document. Must not be {@literal null}.
     * @param options the {@link FindAndModifyOptions} holding additional information. Must not be {@literal null}.
     * @return the converted object that was updated or {@literal null}, if not found. Depending on the value of
     *         {@link FindAndReplaceOptions#isReturnNew()} this will either be the object as it was before the update or
     *         as it is after the update.
     * @since 2.1
     */
    public <T> T findAndReplace(Query query, T replacement, FindAndReplaceOptions options, String collectionName) {
        return findAndReplace(query, replacement, options, (Class<T>) ClassUtils.getUserClass(replacement), collectionName);
    }

    /**
     * Triggers
     * <a href="https://docs.mongodb.com/manual/reference/method/db.collection.findOneAndReplace/">findOneAndReplace<a/>
     * to replace a single document matching {@link Criteria} of given {@link Query} with the {@code replacement} document
     * taking {@link FindAndReplaceOptions} into account.<br />
     * <strong>NOTE:</strong> The replacement entity must not hold an {@literal id}.
     *
     * @param query the {@link Query} class that specifies the {@link Criteria} used to find a record and also an optional
     *          fields specification. Must not be {@literal null}.
     * @param replacement the replacement document. Must not be {@literal null}.
     * @param options the {@link FindAndModifyOptions} holding additional information. Must not be {@literal null}.
     * @param entityType the parametrized type. Must not be {@literal null}.
     * @param collectionName the collection to query. Must not be {@literal null}.
     * @return the converted object that was updated or {@literal null}, if not found. Depending on the value of
     *         {@link FindAndReplaceOptions#isReturnNew()} this will either be the object as it was before the update or
     *         as it is after the update.
     * @since 2.1
     */
    public <T> T findAndReplace(Query query, T replacement, FindAndReplaceOptions options, Class<T> entityType, String collectionName) {
        return findAndReplace(query, replacement, options, entityType, collectionName, entityType);
    }

    /**
     * Triggers
     * <a href="https://docs.mongodb.com/manual/reference/method/db.collection.findOneAndReplace/">findOneAndReplace<a/>
     * to replace a single document matching {@link Criteria} of given {@link Query} with the {@code replacement} document
     * taking {@link FindAndReplaceOptions} into account.<br />
     * <strong>NOTE:</strong> The replacement entity must not hold an {@literal id}.
     *
     * @param query the {@link Query} class that specifies the {@link Criteria} used to find a record and also an optional
     *          fields specification. Must not be {@literal null}.
     * @param replacement the replacement document. Must not be {@literal null}.
     * @param options the {@link FindAndModifyOptions} holding additional information. Must not be {@literal null}.
     * @param entityType the type used for mapping the {@link Query} to domain type fields and deriving the collection
     *          from. Must not be {@literal null}.
     * @param resultType the parametrized type projection return type. Must not be {@literal null}, use the domain type of
     *          {@code Object.class} instead.
     * @return the converted object that was updated or {@literal null}, if not found. Depending on the value of
     *         {@link FindAndReplaceOptions#isReturnNew()} this will either be the object as it was before the update or
     *         as it is after the update.
     * @since 2.1
     */
    public <S, T> T findAndReplace(Query query, S replacement, FindAndReplaceOptions options, Class<S> entityType, Class<T> resultType) {
        return findAndReplace(query, replacement, options, entityType,
                getCollectionName(ClassUtils.getUserClass(entityType)), resultType);
    }

    /**
     * Triggers
     * <a href="https://docs.mongodb.com/manual/reference/method/db.collection.findOneAndReplace/">findOneAndReplace<a/>
     * to replace a single document matching {@link Criteria} of given {@link Query} with the {@code replacement} document
     * taking {@link FindAndReplaceOptions} into account.<br />
     * <strong>NOTE:</strong> The replacement entity must not hold an {@literal id}.
     *
     * @param query the {@link Query} class that specifies the {@link Criteria} used to find a record and also an optional
     *          fields specification. Must not be {@literal null}.
     * @param replacement the replacement document. Must not be {@literal null}.
     * @param options the {@link FindAndModifyOptions} holding additional information. Must not be {@literal null}.
     * @param entityType the type used for mapping the {@link Query} to domain type fields. Must not be {@literal null}.
     * @param collectionName the collection to query. Must not be {@literal null}.
     * @param resultType the parametrized type projection return type. Must not be {@literal null}, use the domain type of
     *          {@code Object.class} instead.
     * @return the converted object that was updated or {@literal null}, if not found. Depending on the value of
     *         {@link FindAndReplaceOptions#isReturnNew()} this will either be the object as it was before the update or
     *         as it is after the update.
     * @since 2.1
     */
    public <S, T> T findAndReplace(Query query, S replacement, FindAndReplaceOptions options, Class<S> entityType,
                            String collectionName, Class<T> resultType){
        return mongoTemplate.findAndReplace(query, replacement, options, entityType, collectionName, resultType);
    }

    /**
     * Map the results of an ad-hoc query on the collection for the entity type to a single instance of an object of the
     * specified type. The first document that matches the query is returned and also removed from the collection in the
     * database.
     * <p/>
     * The object is converted from the MongoDB native representation using an instance of {@see MongoConverter}.
     * <p/>
     * The query is specified as a {@link Query} which can be created either using the {@link BasicQuery} or the more
     * feature rich {@link Query}.
     *
     * @param query the query class that specifies the criteria used to find a record and also an optional fields
     *          specification.
     * @param entityClass the parametrized type of the returned list.
     * @return the converted object
     */
    public <T> T findAndRemove(Query query, Class<T> entityClass){
        return mongoTemplate.findAndRemove(query, entityClass);
    }

    /**
     * Map the results of an ad-hoc query on the specified collection to a single instance of an object of the specified
     * type. The first document that matches the query is returned and also removed from the collection in the database.
     * <p/>
     * The object is converted from the MongoDB native representation using an instance of {@see MongoConverter}. Unless
     * configured otherwise, an instance of {@link MappingMongoConverter} will be used.
     * <p/>
     * The query is specified as a {@link Query} which can be created either using the {@link BasicQuery} or the more
     * feature rich {@link Query}.
     *
     * @param query the query class that specifies the criteria used to find a record and also an optional fields
     *          specification.
     * @param entityClass the parametrized type of the returned list.
     * @param collectionName name of the collection to retrieve the objects from.
     * @return the converted object.
     */
    public <T> T findAndRemove(Query query, Class<T> entityClass, String collectionName){
        return mongoTemplate.findAndRemove(query, entityClass, collectionName);
    }

    /**
     * Returns the number of documents for the given {@link Query} by querying the collection of the given entity class.
     *
     * @param query the {@link Query} class that specifies the criteria used to find documents. Must not be
     *          {@literal null}.
     * @param entityClass class that determines the collection to use. Must not be {@literal null}.
     * @return the count of matching documents.
     */
    public long count(Query query, Class<?> entityClass){
        return mongoTemplate.count(query, entityClass);
    }

    /**
     * Returns the number of documents for the given {@link Query} querying the given collection. The given {@link Query}
     * must solely consist of document field references as we lack type information to map potential property references
     * onto document fields. Use {@link #count(Query, Class, String)} to get full type specific support.
     *
     * @param query the {@link Query} class that specifies the criteria used to find documents.
     * @param collectionName must not be {@literal null} or empty.
     * @return the count of matching documents.
     * @see #count(Query, Class, String)
     */
    public long count(Query query, String collectionName){
        return mongoTemplate.count(query, collectionName);
    }

    /**
     * Returns the number of documents for the given {@link Query} by querying the given collection using the given entity
     * class to map the given {@link Query}.
     *
     * @param query the {@link Query} class that specifies the criteria used to find documents. Must not be
     *          {@literal null}.
     * @param entityClass the parametrized type. Can be {@literal null}.
     * @param collectionName must not be {@literal null} or empty.
     * @return the count of matching documents.
     */
    public long count(Query query, Class<?> entityClass, String collectionName){
        return mongoTemplate.count(query, entityClass, collectionName);
    }

    /**
     * Insert the object into the collection for the entity type of the object to save.
     * <p/>
     * The object is converted to the MongoDB native representation using an instance of {@see MongoConverter}.
     * <p/>
     * If you object has an "Id' property, it will be set with the generated Id from MongoDB. If your Id property is a
     * String then MongoDB ObjectId will be used to populate that string. Otherwise, the conversion from ObjectId to your
     * property type will be handled by Spring's BeanWrapper class that leverages Type Conversion API. See
     * <a href="http://docs.spring.io/spring/docs/current/spring-framework-reference/core.html#validation" > Spring's Type
     * Conversion"</a> for more details.
     * <p/>
     * <p/>
     * Insert is used to initially store the object into the database. To update an existing object use the save method.
     *
     * @param objectToSave the object to store in the collection. Must not be {@literal null}.
     * @return the inserted object.
     */
    public <T> T insert(T objectToSave){
        return mongoTemplate.insert(objectToSave);
    }

    /**
     * Insert the object into the specified collection.
     * <p/>
     * The object is converted to the MongoDB native representation using an instance of {@see MongoConverter}. Unless
     * configured otherwise, an instance of {@link MappingMongoConverter} will be used.
     * <p/>
     * Insert is used to initially store the object into the database. To update an existing object use the save method.
     *
     * @param objectToSave the object to store in the collection. Must not be {@literal null}.
     * @param collectionName name of the collection to store the object in. Must not be {@literal null}.
     * @return the inserted object.
     */
    public <T> T insert(T objectToSave, String collectionName){
        return mongoTemplate.insert(objectToSave, collectionName);
    }

    /**
     * Insert a Collection of objects into a collection in a single batch write to the database.
     *
     * @param batchToSave the batch of objects to save. Must not be {@literal null}.
     * @param entityClass class that determines the collection to use. Must not be {@literal null}.
     * @return the inserted objects that.
     */
    public <T> Collection<T> insert(Collection<? extends T> batchToSave, Class<?> entityClass){
        return mongoTemplate.insert(batchToSave, entityClass);
    }

    /**
     * Insert a batch of objects into the specified collection in a single batch write to the database.
     *
     * @param batchToSave the list of objects to save. Must not be {@literal null}.
     * @param collectionName name of the collection to store the object in. Must not be {@literal null}.
     * @return the inserted objects that.
     */
    public <T> Collection<T> insert(Collection<? extends T> batchToSave, String collectionName){
        return mongoTemplate.insert(batchToSave, collectionName);
    }

    /**
     * Insert a mixed Collection of objects into a database collection determining the collection name to use based on the
     * class.
     *
     * @param objectsToSave the list of objects to save. Must not be {@literal null}.
     * @return the inserted objects.
     */
    public <T> Collection<T> insertAll(Collection<? extends T> objectsToSave){
        return mongoTemplate.insertAll(objectsToSave);
    }

    /**
     * Save the object to the collection for the entity type of the object to save. This will perform an insert if the
     * object is not already present, that is an 'upsert'.
     * <p/>
     * The object is converted to the MongoDB native representation using an instance of {@see MongoConverter}. Unless
     * configured otherwise, an instance of {@link MappingMongoConverter} will be used.
     * <p/>
     * If you object has an "Id' property, it will be set with the generated Id from MongoDB. If your Id property is a
     * String then MongoDB ObjectId will be used to populate that string. Otherwise, the conversion from ObjectId to your
     * property type will be handled by Spring's BeanWrapper class that leverages Type Conversion API. See
     * <a href="http://docs.spring.io/spring/docs/current/spring-framework-reference/core.html#validation" > Spring's Type
     * Conversion"</a> for more details.
     *
     * @param objectToSave the object to store in the collection. Must not be {@literal null}.
     * @return the saved object.
     */
    public <T> T save(T objectToSave){
        return mongoTemplate.save(objectToSave);
    }

    /**
     * Save the object to the specified collection. This will perform an insert if the object is not already present, that
     * is an 'upsert'.
     * <p/>
     * The object is converted to the MongoDB native representation using an instance of {@see MongoConverter}. Unless
     * configured otherwise, an instance of {@link MappingMongoConverter} will be used.
     * <p/>
     * If you object has an "Id' property, it will be set with the generated Id from MongoDB. If your Id property is a
     * String then MongoDB ObjectId will be used to populate that string. Otherwise, the conversion from ObjectId to your
     * property type will be handled by Spring's BeanWrapper class that leverages Type Conversion API. See <a
     * http://docs.spring.io/spring/docs/current/spring-framework-reference/core.html#validation">Spring's Type
     * Conversion"</a> for more details.
     *
     * @param objectToSave the object to store in the collection. Must not be {@literal null}.
     * @param collectionName name of the collection to store the object in. Must not be {@literal null}.
     * @return the saved object.
     */
    public <T> T save(T objectToSave, String collectionName){
        return mongoTemplate.save(objectToSave, collectionName);
    }

    /**
     * Performs an upsert. If no document is found that matches the query, a new document is created and inserted by
     * combining the query document and the update document.
     *
     * @param query the query document that specifies the criteria used to select a record to be upserted. Must not be
     *          {@literal null}.
     * @param update the update document that contains the updated object or $ operators to manipulate the existing
     *          object. Must not be {@literal null}.
     * @param entityClass class that determines the collection to use. Must not be {@literal null}.
     * @return the {@link UpdateResult} which lets you access the results of the previous write.
     */
    public UpdateResult upsert(Query query, Update update, Class<?> entityClass){
        return mongoTemplate.upsert(query, update, entityClass);
    }

    /**
     * Performs an upsert. If no document is found that matches the query, a new document is created and inserted by
     * combining the query document and the update document. <br />
     * <strong>NOTE:</strong> Any additional support for field mapping, versions, etc. is not available due to the lack of
     * domain type information. Use {@link #upsert(Query, Update, Class, String)} to get full type specific support.
     *
     * @param query the query document that specifies the criteria used to select a record to be upserted. Must not be
     *          {@literal null}.
     * @param update the update document that contains the updated object or $ operators to manipulate the existing
     *          object. Must not be {@literal null}.
     * @param collectionName name of the collection to update the object in.
     * @return the {@link UpdateResult} which lets you access the results of the previous write.
     */
    public UpdateResult upsert(Query query, Update update, String collectionName){
        return mongoTemplate.upsert(query, update, collectionName);
    }

    /**
     * Performs an upsert. If no document is found that matches the query, a new document is created and inserted by
     * combining the query document and the update document.
     *
     * @param query the query document that specifies the criteria used to select a record to be upserted. Must not be
     *          {@literal null}.
     * @param update the update document that contains the updated object or $ operators to manipulate the existing
     *          object. Must not be {@literal null}.
     * @param entityClass class of the pojo to be operated on. Must not be {@literal null}.
     * @param collectionName name of the collection to update the object in. Must not be {@literal null}.
     * @return the {@link UpdateResult} which lets you access the results of the previous write.
     */
    public UpdateResult upsert(Query query, Update update, Class<?> entityClass, String collectionName){
        return mongoTemplate.upsert(query, update, entityClass, collectionName);
    }

    /**
     * Updates the first object that is found in the collection of the entity class that matches the query document with
     * the provided update document.
     *
     * @param query the query document that specifies the criteria used to select a record to be updated. Must not be
     *          {@literal null}.
     * @param update the update document that contains the updated object or $ operators to manipulate the existing. Must
     *          not be {@literal null}.
     * @param entityClass class that determines the collection to use.
     * @return the {@link UpdateResult} which lets you access the results of the previous write.
     */
    public UpdateResult updateFirst(Query query, Update update, Class<?> entityClass){
        return mongoTemplate.updateFirst(query, update, entityClass);
    }

    /**
     * Updates the first object that is found in the specified collection that matches the query document criteria with
     * the provided updated document. <br />
     * <strong>NOTE:</strong> Any additional support for field mapping, versions, etc. is not available due to the lack of
     * domain type information. Use {@link #updateFirst(Query, Update, Class, String)} to get full type specific support.
     *
     * @param query the query document that specifies the criteria used to select a record to be updated. Must not be
     *          {@literal null}.
     * @param update the update document that contains the updated object or $ operators to manipulate the existing. Must
     *          not be {@literal null}.
     * @param collectionName name of the collection to update the object in. Must not be {@literal null}.
     * @return the {@link UpdateResult} which lets you access the results of the previous write.
     */
    public UpdateResult updateFirst(Query query, Update update, String collectionName){
        return mongoTemplate.updateFirst(query, update, collectionName);
    }

    /**
     * Updates the first object that is found in the specified collection that matches the query document criteria with
     * the provided updated document. <br />
     *
     * @param query the query document that specifies the criteria used to select a record to be updated. Must not be
     *          {@literal null}.
     * @param update the update document that contains the updated object or $ operators to manipulate the existing. Must
     *          not be {@literal null}.
     * @param entityClass class of the pojo to be operated on. Must not be {@literal null}.
     * @param collectionName name of the collection to update the object in. Must not be {@literal null}.
     * @return the {@link UpdateResult} which lets you access the results of the previous write.
     */
    public UpdateResult updateFirst(Query query, Update update, Class<?> entityClass, String collectionName){
        return mongoTemplate.updateFirst(query, update, entityClass, collectionName);
    }

    /**
     * Updates all objects that are found in the collection for the entity class that matches the query document criteria
     * with the provided updated document.
     *
     * @param query the query document that specifies the criteria used to select a record to be updated. Must not be
     *          {@literal null}.
     * @param update the update document that contains the updated object or $ operators to manipulate the existing. Must
     *          not be {@literal null}.
     * @param entityClass class of the pojo to be operated on. Must not be {@literal null}.
     * @return the {@link UpdateResult} which lets you access the results of the previous write.
     */
    public UpdateResult updateMulti(Query query, Update update, Class<?> entityClass){
        return mongoTemplate.updateFirst(query, update, entityClass);
    }

    /**
     * Updates all objects that are found in the specified collection that matches the query document criteria with the
     * provided updated document. <br />
     * <strong>NOTE:</strong> Any additional support for field mapping, versions, etc. is not available due to the lack of
     * domain type information. Use {@link #updateMulti(Query, Update, Class, String)} to get full type specific support.
     *
     * @param query the query document that specifies the criteria used to select a record to be updated. Must not be
     *          {@literal null}.
     * @param update the update document that contains the updated object or $ operators to manipulate the existing. Must
     *          not be {@literal null}.
     * @param collectionName name of the collection to update the object in. Must not be {@literal null}.
     * @return the {@link UpdateResult} which lets you access the results of the previous write.
     */
    public UpdateResult updateMulti(Query query, Update update, String collectionName){
        return mongoTemplate.updateMulti(query, update, collectionName);
    }

    /**
     * Updates all objects that are found in the collection for the entity class that matches the query document criteria
     * with the provided updated document.
     *
     * @param query the query document that specifies the criteria used to select a record to be updated. Must not be
     *          {@literal null}.
     * @param update the update document that contains the updated object or $ operators to manipulate the existing. Must
     *          not be {@literal null}.
     * @param entityClass class of the pojo to be operated on. Must not be {@literal null}.
     * @param collectionName name of the collection to update the object in. Must not be {@literal null}.
     * @return the {@link UpdateResult} which lets you access the results of the previous write.
     */
    public UpdateResult updateMulti(Query query, Update update, Class<?> entityClass, String collectionName){
        return mongoTemplate.updateMulti(query, update, entityClass, collectionName);
    }

    /**
     * Remove the given object from the collection by id.
     *
     * @param object must not be {@literal null}.
     * @return the {@link DeleteResult} which lets you access the results of the previous delete.
     */
    public DeleteResult remove(Object object){
        return mongoTemplate.remove(object);
    }

    /**
     * Removes the given object from the given collection.
     *
     * @param object must not be {@literal null}.
     * @param collectionName name of the collection where the objects will removed, must not be {@literal null} or empty.
     * @return the {@link DeleteResult} which lets you access the results of the previous delete.
     */
    public DeleteResult remove(Object object, String collectionName){
        return mongoTemplate.remove(object, collectionName);
    }

    /**
     * Remove all documents that match the provided query document criteria from the the collection used to store the
     * entityClass. The Class parameter is also used to help convert the Id of the object if it is present in the query.
     *
     * @param query the query document that specifies the criteria used to remove a record.
     * @param entityClass class that determines the collection to use.
     * @return the {@link DeleteResult} which lets you access the results of the previous delete.
     * @throws IllegalArgumentException when {@literal query} or {@literal entityClass} is {@literal null}.
     */
    public DeleteResult remove(Query query, Class<?> entityClass){
        return mongoTemplate.remove(query, entityClass);
    }

    /**
     * Remove all documents that match the provided query document criteria from the the collection used to store the
     * entityClass. The Class parameter is also used to help convert the Id of the object if it is present in the query.
     *
     * @param query the query document that specifies the criteria used to remove a record.
     * @param entityClass class of the pojo to be operated on. Can be {@literal null}.
     * @param collectionName name of the collection where the objects will removed, must not be {@literal null} or empty.
     * @return the {@link DeleteResult} which lets you access the results of the previous delete.
     * @throws IllegalArgumentException when {@literal query}, {@literal entityClass} or {@literal collectionName} is
     *           {@literal null}.
     */
    public DeleteResult remove(Query query, Class<?> entityClass, String collectionName){
        return mongoTemplate.remove(query, entityClass, collectionName);
    }

    /**
     * Remove all documents from the specified collection that match the provided query document criteria. There is no
     * conversion/mapping done for any criteria using the id field. <br />
     * <strong>NOTE:</strong> Any additional support for field mapping is not available due to the lack of domain type
     * information. Use {@link #remove(Query, Class, String)} to get full type specific support.
     *
     * @param query the query document that specifies the criteria used to remove a record.
     * @param collectionName name of the collection where the objects will removed, must not be {@literal null} or empty.
     * @return the {@link DeleteResult} which lets you access the results of the previous delete.
     * @throws IllegalArgumentException when {@literal query} or {@literal collectionName} is {@literal null}.
     */
    public DeleteResult remove(Query query, String collectionName){
        return mongoTemplate.remove(query, collectionName);
    }

    /**
     * Returns and removes all documents form the specified collection that match the provided query. <br />
     * <strong>NOTE:</strong> Any additional support for field mapping is not available due to the lack of domain type
     * information. Use {@link #findAllAndRemove(Query, Class, String)} to get full type specific support.
     *
     * @param query the query document that specifies the criteria used to find and remove documents.
     * @param collectionName name of the collection where the objects will removed, must not be {@literal null} or empty.
     * @return the {@link List} converted objects deleted by this operation.
     * @since 1.5
     */
    public <T> List<T> findAllAndRemove(Query query, String collectionName){
        return mongoTemplate.findAllAndRemove(query, collectionName);
    }

    /**
     * Returns and removes all documents matching the given query form the collection used to store the entityClass.
     *
     * @param query the query document that specifies the criteria used to find and remove documents.
     * @param entityClass class of the pojo to be operated on.
     * @return the {@link List} converted objects deleted by this operation.
     * @since 1.5
     */
    public <T> List<T> findAllAndRemove(Query query, Class<T> entityClass){
        return mongoTemplate.findAllAndRemove(query, entityClass);
    }

    /**
     * Returns and removes all documents that match the provided query document criteria from the the collection used to
     * store the entityClass. The Class parameter is also used to help convert the Id of the object if it is present in
     * the query.
     *
     * @param query the query document that specifies the criteria used to find and remove documents.
     * @param entityClass class of the pojo to be operated on.
     * @param collectionName name of the collection where the objects will removed, must not be {@literal null} or empty.
     * @return the {@link List} converted objects deleted by this operation.
     * @since 1.5
     */
    public <T> List<T> findAllAndRemove(Query query, Class<T> entityClass, String collectionName){
        return mongoTemplate.findAllAndRemove(query, entityClass, collectionName);
    }

    /* ********************************************************************************
     * BulkOperations
     * ******************************************************************************** */

//    /**
//     * 批量新增
//     *
//     * @param documents
//     * @param collectionName
//     * @return
//     */
//    public <T> BulkWriteResult bulkInsert(List<T> documents, String collectionName) {
//        return mongoTemplate.bulkOps(BulkOperations.BulkMode.ORDERED, collectionName).insert(documents).execute();
//    }
//
//    /**
//     * 批量新增
//     *
//     * @param documents
//     * @param entityType
//     * @param collectionName
//     * @return
//     */
//    public <T> BulkWriteResult bulkInsert(List<T> documents, Class<?> entityType, String collectionName) {
//        return mongoTemplate.bulkOps(BulkOperations.BulkMode.ORDERED, entityType, collectionName).insert(documents).execute();
//    }

    /**
     * 批量新增
     *
     * @param documents
     * @param collectionName
     * @return
     */
    public BulkWriteResult bulkInsert(List<? extends Object> documents, String collectionName) {
        return mongoTemplate.bulkOps(BulkOperations.BulkMode.ORDERED, collectionName).insert(documents).execute();
    }

    /**
     * 批量新增
     *
     * @param documents
     * @param entityType
     * @param collectionName
     * @return
     */
    public BulkWriteResult bulkInsert(List<? extends Object> documents, Class<?> entityType, String collectionName) {
        return mongoTemplate.bulkOps(BulkOperations.BulkMode.ORDERED, entityType, collectionName).insert(documents).execute();
    }
}
