package com.spring.boot.pulsar.controller;

import org.apache.pulsar.client.api.Producer;
import org.apache.pulsar.client.api.PulsarClientException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>Description: </p>
 *
 * @author rock.jiang
 * @date 2021/4/25 16:57
 */
@RequestMapping("/producer")
@RestController
public class ProducerController {
    @Autowired
    private Producer<String> myTopicProducer;

    /**
     * localhost:8080/producer/sendMessageToMyTopic
     *
     * @return
     */
    @RequestMapping("/sendMessageToMyTopic")
    public String sendMessageToMyTopic(){
        try {
            myTopicProducer.send("this is a web message");
        } catch (PulsarClientException e) {
            e.printStackTrace();
            return "failed";
        }

        return "ok";
    }
}
