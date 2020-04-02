package com.spring.boot.rabbitmq.config;

import com.rabbitmq.client.BlockedListener;
import com.spring.boot.rabbitmq.listener.RabbitBlockedListener;
import com.spring.boot.rabbitmq.listener.RabbitConnectionListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

/**
 * <p>Description: </p>
 *
 * @author rock.jiang
 * Date 2020/03/31 09:18
 */
@Slf4j
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
    public Queue userQueue() { return new Queue("userQueue"); }

    @Bean
    public Queue sendAndReceiveQueue() {
        return new Queue("sendAndReceiveQueue");
    }

    /**
     * 订单Queue
     * 订单生成之后，将订单消息放到订单队列，库存队列从订单队列中获取订单消息，处理成功之后发送库存处理成功消息，然后订单定时任务从库存成功处理Queue中
     * 获取成功处理消息，完成分布式事务
     *
     * @return
     */
    @Bean
    public Queue orderQueue() {
        return new Queue("orderQueue");
    }

    /**
     * 库存Queue
     *
     * @return
     */
    @Bean
    public Queue stockQueue() { return new Queue("stockQueue"); }

    /**
     * 库存成功处理Queue
     *
     * @return
     */
    @Bean
    public Queue stockSuccessQueue() { return new Queue("stockSuccessQueue"); }


    /**
     * 配置中设置
     */
//    @Bean
//    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
//        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
//
//        rabbitTemplate.setMandatory(true);
//        rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {
//            log.debug("ConfirmCallback correlationData: {} ack: {} cause: {}", correlationData, ack, cause);
//        });
//
//        rabbitTemplate.setReturnCallback((message, replyCode, replyText, exchange, routingKey) -> {
//            log.debug("ReturnCallback message: {} replyCode: {} replyText: {} exchange: {} routingKey: {}", message, replyCode, replyText, exchange, routingKey);
//        });
//
//        rabbitTemplate.setUsePublisherConnection(true);
//
//        return rabbitTemplate;
//    }
}
