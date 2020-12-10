package com.spring.boot.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.test.EmbeddedKafkaBroker;
import org.springframework.kafka.test.context.EmbeddedKafka;

@EmbeddedKafka
class EmbeddedKafkaConditionTests {

    @Autowired
    private KafkaTemplate<String, String> template;

    /**
     * EmbeddedKafkaBroker broker 只能用在方法参数中，@Autowired 无效
     *
     * @param broker
     */
    @Test
    void test(EmbeddedKafkaBroker broker) {
        String brokerList = broker.getBrokersAsString();
        System.out.println(brokerList); // output: 127.0.0.1:62513

        broker.addTopics(
                new NewTopic("thing1", 10, (short) 1),
                new NewTopic("thing2", 15, (short) 1),
                new NewTopic("streaming_topic2", 15, (short) 1)
        );

//        template.sendDefault("thing1", "bar");

//        Map<String, Object> consumerProps = KafkaTestUtils.consumerProps("testGroup", "true", broker);
//        consumerProps.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
//        ConsumerFactory<Integer, String> cf = new DefaultKafkaConsumerFactory<>(consumerProps);
//        Consumer<Integer, String> consumer = cf.createConsumer();
//        broker.consumeFromAnEmbeddedTopic(consumer, "thing1");
//
//        ConsumerRecord<Integer, String> received = KafkaTestUtils.getSingleRecord(consumer, "thing1");

//        Map<String, Object> consumerProps = KafkaTestUtils.consumerProps("testGroup", "true", broker);
//        consumerProps.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
//        ConsumerFactory<Integer, String> cf = new DefaultKafkaConsumerFactory<>(consumerProps);
//        Consumer<Integer, String> consumer = cf.createConsumer();
//        broker.consumeFromAnEmbeddedTopic(consumer, "streaming_topic2");
//        ConsumerRecords<Integer, String> replies = KafkaTestUtils.getRecords(consumer);
//        assertThat(replies.count()).isGreaterThanOrEqualTo(1);
    }
}
