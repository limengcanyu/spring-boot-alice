package com.spring.boot.kafka.streams;

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
    private KafkaTemplate kafkaTemplate;

    @Test
    public void sendStringMessage() {
        kafkaTemplate.send("ks1In", "ks1In message 000001");
        kafkaTemplate.send("ks1In", "ks1In message 000002");

    }

}
