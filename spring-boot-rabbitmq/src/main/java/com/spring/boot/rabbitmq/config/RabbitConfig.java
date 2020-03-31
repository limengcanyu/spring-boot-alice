package com.spring.boot.rabbitmq.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>Description: </p>
 *
 * @author rock.jiang
 * Date 2020/03/31 09:18
 */
@Configuration
public class RabbitConfig {

    /**
     * Queue定义，应用启动后自动创建Queue
     *
     * @return
     */
    @Bean
    public Queue myQueue() {
        return new Queue("myqueue");
    }

    @Bean
    public Queue userQueue() {
        return new Queue("userQueue");
    }

    @Bean
    public Queue sendAndReceiveQueue() {
        return new Queue("sendAndReceiveQueue");
    }
}
