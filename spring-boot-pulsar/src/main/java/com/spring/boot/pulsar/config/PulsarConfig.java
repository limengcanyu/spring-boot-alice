package com.spring.boot.pulsar.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.pulsar.client.api.*;
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

    private static final String SERVICE_URL = "pulsar://192.168.203.132:6650";
    private static final String MY_TOPIC = "my-topic";
    private static final String SUBSCRIPTION_NAME = "my-subscription";

    @Bean
    Producer<String> myTopicProducer() {
        try {
            PulsarClient client = PulsarClient.builder()
                    .serviceUrl(SERVICE_URL)
                    .build();

            return client.newProducer(Schema.STRING)
                    .topic(MY_TOPIC)
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
                    .serviceUrl(SERVICE_URL)
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

    // 创建消费者方式1
//    @Bean
//    PulsarConsumer topic1Consumer() {
//        PulsarConsumer pulsarConsumer = new PulsarConsumer(SERVICE_URL, MY_TOPIC, SUBSCRIPTION_NAME);
//        pulsarConsumer.receive();
//        return pulsarConsumer;
//    }

    // 创建消费者方式2
    @Bean
    Consumer<String> topic1Consumer() {
        Consumer<String> consumer = null;

        try {
            PulsarClient client = PulsarClient.builder()
                    .serviceUrl(SERVICE_URL)
                    .build();

            // 消费者（Consumer）
            consumer = client.newConsumer(Schema.STRING)
                    .topic(MY_TOPIC)
                    .subscriptionName(SUBSCRIPTION_NAME)
                    .ackTimeout(10, TimeUnit.SECONDS)
                    .subscriptionType(SubscriptionType.Exclusive)
                    .messageListener(new MessageListener<String>() {
                        @Override
                        public void received(Consumer<String> consumer, Message<String> message) {

                            consumer.negativeAcknowledge(message);

//                            try {
//                                System.out.println("topic1Consumer received message: " + new String(message.getData()));
//
//                                consumer.acknowledge(message);
//                            } catch (PulsarClientException e) {
//                                consumer.negativeAcknowledge(message);
//                                e.printStackTrace();
//                            }
                        }
                    })
                    .subscribe();
        } catch (PulsarClientException e) {
            log.debug("=== config bean topic1Consumer failed!");
            e.printStackTrace();
        }

        return consumer;
    }

}
