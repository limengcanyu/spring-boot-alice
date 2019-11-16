package com.spring.boot.mongo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.ScriptOperations;
import org.springframework.data.mongodb.core.script.ExecutableMongoScript;
import org.springframework.data.mongodb.core.script.NamedMongoScript;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ScriptOperationsTest {
    private static final Logger logger = LoggerFactory.getLogger(ScriptOperationsTest.class);

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private MongoOperations mongoOps;

    @Test
    public void query() {
        ScriptOperations scriptOps = mongoTemplate.scriptOps();

        ExecutableMongoScript echoScript = new ExecutableMongoScript("function(x) { return x; }");
        scriptOps.execute(echoScript, "directly execute script");

        scriptOps.register(new NamedMongoScript("echo", echoScript));
        scriptOps.call("echo", "execute script via name");
    }
}
