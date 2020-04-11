package com.spring.boot.mongo;


import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import org.bson.Document;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.TypedAggregation;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.data.domain.Sort.Direction.ASC;
import static org.springframework.data.domain.Sort.Direction.DESC;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;
import static org.springframework.data.mongodb.core.query.Criteria.where;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AggregationOperationsTest {
    private static final Logger logger = LoggerFactory.getLogger(AggregationOperationsTest.class);

    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    public void groupTest() {
        List<Document> documentList = new ArrayList<>();

        for (int t = 1; t <= 5; t++) {
            for (int k = 1; k <= 3; k++) {
                for (int i = 1; i <= 5; i++) {
                    Document document = new Document();
                    document.put("tenant_id", "tenant_00000" + t);
                    document.put("company_id", "company_000001");
                    document.put("salary_month", "2019-11");
                    document.put("salary_batch", k);
                    document.put("employee_id", "employee_00000" + i);

                    for (int j = 1; j <= 100; j++) {
                        document.put("item_00" + j, 1.0 + j);
                    }
                    documentList.add(document);
                }
            }
        }

        logger.debug("documentList: {}", JSONObject.toJSONString(documentList));

        mongoTemplate.insert(documentList, "group_test");

        Aggregation aggregation = newAggregation(
                group("tenant_id", "company_id", "salary_month").sum("item_001").as("item001"),
                sort(ASC, "tenant_id", "company_id", "salary_month", "item001")
        );

        AggregationResults<Document> result = mongoTemplate.aggregate(aggregation, "group_test", Document.class);
        List<Document> mappedResults = result.getMappedResults();
        logger.debug("mappedResults: {}", JSONObject.toJSONString(mappedResults));

        mongoTemplate.dropCollection("group_test");
    }

    @Data
    public class TagCount {
        String tag;
        int n;
    }

    @Test
    public void example1() {
        Document document = new Document();
        document.put("tags", "tag1");
        document.put("x", 1);
        mongoTemplate.save(document, "tags");

        document = new Document();
        document.put("tags", "tag1");
        document.put("x", 2);
        mongoTemplate.save(document, "tags");

        document = new Document();
        document.put("tags", "tag2");
        document.put("x", 1);
        mongoTemplate.save(document, "tags");

        Aggregation agg = newAggregation(
                // 选中操作字段"tags"
                project("tags"),
                // 每个操作字段生成一个新的文档
                unwind("tags"),
                // 对字段"tags"分组，计数
                group("tags").count().as("n"),
                // previousOperation()：将先前操作的结果投射到当前字段
                // 选中"n"字段，并使用"tag"为先前的分组操作生成的ID字段创建一个别名
                project("n").and("tag").previousOperation(),
                sort(DESC, "n")
        );

        AggregationResults<TagCount> results = mongoTemplate.aggregate(agg, "tags", TagCount.class);
        List<TagCount> tagCount = results.getMappedResults();
        logger.debug("tagCount: {}", tagCount);

        mongoTemplate.dropCollection("tags");
    }

    @Data
    public class ZipInfo {
        String id;
        String city;
        String state;
        @Field("pop")
        int population;
        @Field("loc")
        double[] location;
    }

    @Data
    public class City {
        String name;
        int population;
    }

    @Data
    public class ZipInfoStats {
        String id;
        String state;
        City biggestCity;
        City smallestCity;
    }

    @Test
    public void example2() {
        ZipInfo zipInfo = new ZipInfo();
        zipInfo.setId("1");
        zipInfo.setCity("上海");
        zipInfo.setState("中国");
        zipInfo.setPopulation(1000);
        zipInfo.setLocation(new double[]{100.00, 200.00});
        zipInfo = mongoTemplate.save(zipInfo);
        logger.debug("save: {}", zipInfo);

        zipInfo = new ZipInfo();
        zipInfo.setId("2");
        zipInfo.setCity("北京");
        zipInfo.setState("中国");
        zipInfo.setPopulation(2000); // 人口
        zipInfo.setLocation(new double[]{101.00, 201.00});
        zipInfo = mongoTemplate.save(zipInfo);
        logger.debug("save: {}", zipInfo);

        zipInfo = new ZipInfo();
        zipInfo.setId("3");
        zipInfo.setCity("北京");
        zipInfo.setState("中国");
        zipInfo.setPopulation(1000); // 人口
        zipInfo.setLocation(new double[]{103.00, 203.00});
        zipInfo = mongoTemplate.save(zipInfo);
        logger.debug("save: {}", zipInfo);

        zipInfo = new ZipInfo();
        zipInfo.setId("4");
        zipInfo.setCity("南京");
        zipInfo.setState("中国");
        zipInfo.setPopulation(1000); // 人口
        zipInfo.setLocation(new double[]{103.00, 203.00});
        zipInfo = mongoTemplate.save(zipInfo);
        logger.debug("save: {}", zipInfo);

        zipInfo = new ZipInfo();
        zipInfo.setId("5");
        zipInfo.setCity("加州");
        zipInfo.setState("美国");
        zipInfo.setPopulation(1200); // 人口
        zipInfo.setLocation(new double[]{123.00, 223.00});
        zipInfo = mongoTemplate.save(zipInfo);
        logger.debug("save: {}", zipInfo);

        zipInfo = new ZipInfo();
        zipInfo.setId("6");
        zipInfo.setCity("洛杉矶");
        zipInfo.setState("美国");
        zipInfo.setPopulation(1300); // 人口
        zipInfo.setLocation(new double[]{133.00, 233.00});
        zipInfo = mongoTemplate.save(zipInfo);
        logger.debug("save: {}", zipInfo);

        // (id=1, city=上海, state=中国, population=1000, location=[100.0, 200.0])
        // (id=2, city=北京, state=中国, population=2000, location=[101.0, 201.0])
        // (id=3, city=北京, state=中国, population=1000, location=[103.0, 203.0])
        // (id=4, city=南京, state=中国, population=1000, location=[103.0, 203.0])
        // (id=5, city=加州, state=美国, population=1200, location=[123.0, 223.0])
        // (id=6, city=洛杉矶, state=美国, population=1300, location=[133.0, 233.0])

        TypedAggregation<ZipInfo> aggregation = newAggregation(ZipInfo.class,
                // 根据"state", "city"分组，对"population"求和，结果别名为"pop"
                group("state", "city")
                        .sum("population").as("pop"),
                // 对"pop", "state", "city"按升序排序
                sort(ASC, "pop", "state", "city"),

                // 至此处结果: [
                // {"state":"中国","city":"上海","pop":1000},
                // {"state":"中国","city":"南京","pop":1000},
                // {"state":"美国","city":"加州","pop":1200},
                // {"state":"美国","city":"洛杉矶","pop":1300},
                // {"state":"中国","city":"北京","pop":3000}
                // ]

                // 根据"state"分组，
                group("state")
                        .last("city").as("biggestCity") // 获取最后一个"city"，别名"biggestCity"
                        .last("pop").as("biggestPop") // 获取最后一个"pop"，别名"biggestPop"
                        .first("city").as("smallestCity") // 获取第一个"city"，别名"smallestCity"
                        .first("pop").as("smallestPop"), // 获取第一个"pop"，别名"smallestPop"

                // [
                // {"_id":"美国","biggestCity":"洛杉矶","biggestPop":1300,"smallestCity":"加州","smallestPop":1200},
                // {"_id":"中国","biggestCity":"北京","biggestPop":3000,"smallestCity":"上海","smallestPop":1000}
                // ]

                project().and("state")
                        // 将先前操作的结果投射到当前字段，即project().and("state")中选中的字段
                        .previousOperation()
                        // 根据指定名字定义字段
                        .and("biggestCity")
                        // 在当前字段定义嵌套字段
                        .nested(
                                // 从指定名称和目标创建字段
                                bind("name", "biggestCity").and("population", "biggestPop")
                        )
                        // 根据指定名字定义字段
                        .and("smallestCity")
                        // 在当前字段定义嵌套字段
                        .nested(
                                // 从指定名称和目标创建字段
                                bind("name", "smallestCity").and("population", "smallestPop")
                        ),

                // 至此处结果: [
                // {"biggestCity":{"name":"洛杉矶","population":1300},"smallestCity":{"name":"加州","population":1200},"state":"美国"},
                // {"biggestCity":{"name":"北京","population":3000},"smallestCity":{"name":"上海","population":1000},"state":"中国"}
                // ]

                // 对"state"按升序排序
                sort(ASC, "state")

                // 至此处结果: [
                // {"biggestCity":{"name":"北京","population":3000},"smallestCity":{"name":"上海","population":1000},"state":"中国"},
                // {"biggestCity":{"name":"洛杉矶","population":1300},"smallestCity":{"name":"加州","population":1200},"state":"美国"}
                // ]
        );

        AggregationResults<Document> result = mongoTemplate.aggregate(aggregation, Document.class);
        List<Document> zipInfoStatsList = result.getMappedResults();
        logger.debug("zipInfoStatsList: {}", JSONObject.toJSONString(zipInfoStatsList));

//        AggregationResults<ZipInfoStats> result = mongoTemplate.aggregate(aggregation, ZipInfoStats.class);
//        List<ZipInfoStats> zipInfoStatsList = result.getMappedResults();
//        if (!CollectionUtils.isEmpty(zipInfoStatsList)) {
//            ZipInfoStats firstZipInfoStats = zipInfoStatsList.get(0);
//        }
//
//        System.out.println(zipInfoStatsList);

        mongoTemplate.dropCollection(ZipInfo.class);
    }

    class StateStats {
        @Id
        String id;
        String state;
        @Field("totalPop")
        int totalPopulation;
    }

    @Test
    public void example3() {
        TypedAggregation<ZipInfo> agg = newAggregation(ZipInfo.class,
                // 1. Group the input collection by the state field and calculate the sum of the population field and store the result in the new field "totalPop".
                // 分组之后求和
                group("state").sum("population").as("totalPop"),
                // 2. Sort the intermediate result by the id-reference of the previous group operation in addition to the "totalPop" field in ascending order.
                sort(ASC, previousOperation(), "totalPop"),
                // 3. Filter the intermediate result by using a match operation which accepts a Criteria query as an argument.
                match(where("totalPop").gte(10 * 1000 * 1000))
        );

        AggregationResults<StateStats> result = mongoTemplate.aggregate(agg, StateStats.class);
        List<StateStats> stateStatsList = result.getMappedResults();
        System.out.println();
    }

    class Product {
        String id;
        String name;
        double netPrice;
        int spaceUnits;
    }

    @Test
    public void example4() {
        TypedAggregation<Product> agg = newAggregation(Product.class,
                project("name", "netPrice")
                        .and("netPrice").plus(1).as("netPricePlus1")
                        .and("netPrice").minus(1).as("netPriceMinus1")
                        .and("netPrice").multiply(1.19).as("grossPrice")
                        .and("netPrice").divide(2).as("netPriceDiv2")
                        .and("spaceUnits").mod(2).as("spaceUnitsMod2")
        );

        AggregationResults<Document> result = mongoTemplate.aggregate(agg, Document.class);
        List<Document> resultList = result.getMappedResults();
        System.out.println();
    }

    @Test
    public void example5() {
        TypedAggregation<Product> agg = newAggregation(Product.class,
                project("name", "netPrice")
                        .andExpression("netPrice + 1").as("netPricePlus1")
                        .andExpression("netPrice - 1").as("netPriceMinus1")
                        .andExpression("netPrice / 2").as("netPriceDiv2")
                        .andExpression("netPrice * 1.19").as("grossPrice")
                        .andExpression("spaceUnits % 2").as("spaceUnitsMod2")
                        .andExpression("(netPrice * 0.8  + 1.2) * 1.19").as("grossPriceIncludingDiscountAndCharge")

        );

        AggregationResults<Document> result = mongoTemplate.aggregate(agg, Document.class);
        List<Document> resultList = result.getMappedResults();
        System.out.println();
    }

    @Test
    public void example6() {
        double shippingCosts = 1.2;

        TypedAggregation<Product> agg = newAggregation(Product.class,
                project("name", "netPrice")
                        .andExpression("(netPrice * (1-discountRate)  + [0]) * (1+taxRate)", shippingCosts).as("salesPrice")
        );

        AggregationResults<Document> result = mongoTemplate.aggregate(agg, Document.class);
        List<Document> resultList = result.getMappedResults();
    }

    public class InventoryItem {

        @Id
        int id;
        String item;
        String description;
        int qty;
    }

    public class InventoryItemProjection {

        @Id
        int id;
        String item;
        String description;
        int qty;
        int discount;
    }

    @Test
    public void example7() {
//        TypedAggregation<InventoryItem> agg = newAggregation(InventoryItem.class,
//                project("item").and("discount")
//                        .applyCondition(ConditionalOperator.newBuilder().when(Criteria.where("qty").gte(250))
//                                .then(30)
//                                .otherwise(20))
//                        .and(ifNull("description", "Unspecified")).as("description")
//        );
//
//        AggregationResults<InventoryItemProjection> result = mongoTemplate.aggregate(agg, "inventory", InventoryItemProjection.class);
//        List<InventoryItemProjection> stateStatsList = result.getMappedResults();

        // =============================================================================================================
//        // Conditional aggregation projection
//        TypedAggregation<Book> agg = Aggregation.newAggregation(Book.class,
//                project("title")
//                        .and(ConditionalOperators.when(ComparisonOperators.valueOf("author.middle")
//                                .equalToValue(""))
//                                .then("$$REMOVE")
//                                .otherwiseValueOf("author.middle")
//                        )
//                        .as("author.middle"));
    }
}
