package com.spring.boot.kafka.streams.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Produced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafkaStreams;
import org.springframework.kafka.support.serializer.JsonSerde;

/**
 * <p>Description: </p>
 *
 * @author rock.jiang
 * Date 2020/04/03 11:23
 */
@Configuration(proxyBeanMethods = false)
@EnableKafkaStreams
public class KafkaStreamsConfig {
    @Bean
    public NewTopic streamInTopic() {
        return new NewTopic("ks1In", 1, (short) 1);
    }

    @Bean
    public NewTopic streamOutTopic() {
        return new NewTopic("ks1Out", 1, (short) 1);
    }

    @Bean
    public KStream<Integer, String> kStream(StreamsBuilder streamsBuilder) {
        KStream<Integer, String> stream = streamsBuilder.stream("ks1In");
        stream.map((k, v) -> new KeyValue<>(k, v.toUpperCase())).to("ks1Out",
                Produced.with(Serdes.Integer(), new JsonSerde<>()));
        return stream;
    }
}
