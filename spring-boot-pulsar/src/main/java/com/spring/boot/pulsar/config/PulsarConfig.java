package com.spring.boot.pulsar.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.pulsar.client.api.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

/**
 * <p>Description: </p>
 *
 * @author rock.jiang
 * @date 2021/4/25 13:48
 */
@Slf4j
@Configuration
public class PulsarConfig {

    @Value("${pulsar.url}")
    private String serviceUrl;

    @Bean
    Producer<String> myTopicProducer() {
        try {
            PulsarClient client = PulsarClient.builder()
                    .serviceUrl(serviceUrl)
                    .build();

            return client.newProducer(Schema.STRING)
                    .topic("my-topic")
                    .batchingMaxPublishDelay(10, TimeUnit.MILLISECONDS)
                    .sendTimeout(10, TimeUnit.SECONDS)
                    .blockIfQueueFull(true)
                    .create();
        } catch (PulsarClientException e) {
            log.error("config bean topic1Producer failed!");
            e.printStackTrace();
        }

        return null;
    }

    @Bean
    Producer<String> topic2Producer() {
        try {
            PulsarClient client = PulsarClient.builder()
                    .serviceUrl(serviceUrl)
                    .build();

            return client.newProducer(Schema.STRING)
                    .topic("topic2")
                    .batchingMaxPublishDelay(10, TimeUnit.MILLISECONDS)
                    .sendTimeout(10, TimeUnit.SECONDS)
                    .blockIfQueueFull(true)
                    .create();
        } catch (PulsarClientException e) {
            log.error("config bean topic1Producer failed!");
            e.printStackTrace();
        }

        return null;
    }

    @Bean
    PulsarConsumer topic1Consumer() {
        PulsarConsumer pulsarConsumer = new PulsarConsumer("pulsar://192.168.203.132:6650", "my-topic", "my-subscription");
        pulsarConsumer.receive();
        return pulsarConsumer;
    }
}
