package com.spring.boot.kafka;

import com.spring.boot.kafka.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * <p>Description: KafkaTemplate Test</p>
 *
 * @author rock.jiang
 * date 2019/06/21
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class KafkaTemplateTest {

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    @Test
    public void send() {
//        kafkaTemplate.send("myTopic", "foo1");
//        kafkaTemplate.send("myTopic", "foo2");
//        kafkaTemplate.send("myTopic", "foo3");
//
//        kafkaTemplate.send("sinkTopic", "foo");

        kafkaTemplate.send("stringTopic", "123456");

        kafkaTemplate.send("userTopic", new User("userId_001", "userName_001", "1234567890", null));
    }
}
