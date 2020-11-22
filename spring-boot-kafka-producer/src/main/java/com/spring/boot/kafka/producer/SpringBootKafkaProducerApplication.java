package com.spring.boot.kafka.producer;

import com.spring.boot.kafka.producer.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;

@Slf4j
@SpringBootApplication
public class SpringBootKafkaProducerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootKafkaProducerApplication.class, args);
    }


//    @KafkaListener(topics = "my-replicated-topic")
//    public void myReplicatedTopicProcess(String content) {
//        log.debug("my-replicated-topic receive message: {}", content);
//    }

    @KafkaListener(topics = "someTopic")
    public void someTopicProcess(String content) {
        log.debug("someTopic receive message: {}", content);
    }

    /**
     * 直接接收负载对象
     *
     * @param user
     * @throws Exception
     */
    @KafkaListener(topics = "userTopic")
    public void userTopicProcess(User user) {
        log.debug("userTopic receive message: {}", user);
    }

}
