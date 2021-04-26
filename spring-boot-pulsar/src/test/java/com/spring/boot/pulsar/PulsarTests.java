package com.spring.boot.pulsar;

import org.apache.pulsar.client.api.*;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

/**
 * <p>Description: </p>
 *
 * @author rock.jiang
 * @date 2021/4/25 9:55
 */
public class PulsarTests {
    @Test
    void producer() throws PulsarClientException {
        PulsarClient client = PulsarClient.builder()
                .serviceUrl("pulsar://192.168.203.132:6650")
                .build();
        System.out.println("client: " + client);

        // 生产者（Producer）
        Producer<String> stringProducer = client.newProducer(Schema.STRING)
                .topic("my-topic")
                .batchingMaxPublishDelay(10, TimeUnit.MILLISECONDS)
                .sendTimeout(10, TimeUnit.SECONDS)
                .blockIfQueueFull(true)
                .create();
        stringProducer.send("My message");
        stringProducer.close();
    }

    @Test
    void consumer() throws PulsarClientException {
        PulsarClient client = PulsarClient.builder()
                .serviceUrl("pulsar://192.168.203.132:6650")
                .build();

        // 消费者（Consumer）
        Consumer<String> consumer = client.newConsumer(Schema.STRING)
                .topic("my-topic")
                .subscriptionName("my-subscription")
                .ackTimeout(10, TimeUnit.SECONDS)
                .subscriptionType(SubscriptionType.Exclusive)
                .subscribe();

        System.out.println("wait to receive message...");

        while (true) {
            // Wait for a message
            Message<String> msg = consumer.receive();

            try {
                // Do something with the message
                System.out.printf("| Message received: %s", new String(msg.getData()));

                // Acknowledge the message so that it can be deleted by the message broker
                consumer.acknowledge(msg);
            } catch (Exception e) {
                // Message failed to process, redeliver later
                consumer.negativeAcknowledge(msg);
            }
        }

    }
}
