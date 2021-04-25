package com.spring.boot.pulsar.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.pulsar.client.api.*;

import java.util.concurrent.TimeUnit;

/**
 * <p>Description: </p>
 *
 * @author rock.jiang
 * @date 2021/4/25 13:52
 */
@Slf4j
public class PulsarConsumer {

    private Consumer<String> consumer;

    public PulsarConsumer(String serviceUrl, String topic, String subscriptionName) {
        try {
            PulsarClient client = PulsarClient.builder()
                    .serviceUrl(serviceUrl)
                    .build();

            this.consumer = client.newConsumer(Schema.STRING)
                    .topic(topic)
                    .subscriptionName(subscriptionName)
                    .ackTimeout(10, TimeUnit.SECONDS)
                    // Only the first consumer is allowed to the subscription, other consumers receive an error.
                    // The first consumer receives all 10 messages, and the consuming order is the same as the producing order.
                    // If topic is a partitioned topic, the first consumer subscribes to all partitioned topics,
                    // other consumers are not assigned with partitions and receive an error.
                    .subscriptionType(SubscriptionType.Exclusive)
                    .subscribe();
        } catch (PulsarClientException e) {
            log.debug("构造PulsarConsumer失败！");
            e.printStackTrace();
        }
    }

    public void receive() {
        new Thread(() -> {
            log.info("pulsar consumer will consume message at topic: " + consumer.getTopic());

            while (true) {
                // Wait for a message
                Message<String> msg = null;
                try {
                    msg = consumer.receive();
                } catch (PulsarClientException e) {
                    e.printStackTrace();
                }

                try {
                    // Do something with the message
                    assert msg != null;
                    log.info("=== Message received: {}", new String(msg.getData()));

                    // Acknowledge the message so that it can be deleted by the message broker
                    consumer.acknowledge(msg);
                } catch (Exception e) {
                    // Message failed to process, redeliver later
                    consumer.negativeAcknowledge(msg);
                }
            }
        }).start();
    }
}
