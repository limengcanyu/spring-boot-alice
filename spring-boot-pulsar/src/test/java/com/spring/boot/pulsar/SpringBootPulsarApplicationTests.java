package com.spring.boot.pulsar;

import org.apache.pulsar.client.api.Producer;
import org.apache.pulsar.client.api.PulsarClientException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringBootPulsarApplicationTests {

    @Autowired
    private Producer<String> myTopicProducer;

    @Test
    void myTopicProducer() throws PulsarClientException {
        myTopicProducer.send("My message 111abcd");

        for (int i = 1; i <= 5; i++) {
            myTopicProducer.send("My message " + i);
        }

    }
}
