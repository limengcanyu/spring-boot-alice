package com.spring.boot.mongo;

import com.spring.boot.mongo.entity.Venue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.geo.Box;
import org.springframework.data.geo.Circle;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FluentTemplateAPITest {
    private static final Logger logger = LoggerFactory.getLogger(FluentTemplateAPITest.class);

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private MongoOperations mongoOps;

    @Test
    public void query() {
//        List<SWCharacter> all = mongoOps.find(SWCharacter.class)
//                .inCollection("star-wars")
//                .all();

//        List<Jedi> all = mongoOps.find(SWCharacter.class)
//                .as(Jedi.class)
//                .matching(query(where("jedi").is(true)))
//                .all();

//        GeoResults<Jedi> results = mongoOps.query(SWCharacter.class)
//                .as(Jedi.class)
//                .near(alderaan) // NearQuery.near(-73.9667, 40.78).maxDis…
//                .all();
    }
}
