package com.spring.boot.rabbitmq.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>Description: </p>
 *
 * @author rock.jiang
 * Date 2020/03/31 09:18
 */
@Slf4j
@Configuration
public class RabbitMQConfig {

    /**
     * Queue定义，应用启动后自动创建Queue
     *
     * @return
     */
    @Bean
    public Queue myQueue() {
        return new Queue("myqueue", false, false, true);
    }

    @Bean
    public Queue userQueue() {
        return new Queue("userQueue", false, false, true);
    }

    @Bean
    public Queue sendAndReceiveQueue() {
        return new Queue("sendAndReceiveQueue", false, false, true);
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
        return new Queue("orderQueue", false, false, true);
    }

    /**
     * 库存Queue
     *
     * @return
     */
    @Bean
    public Queue stockQueue() {
        return new Queue("stockQueue", false, false, true);
    }

    /**
     * 库存成功处理Queue
     *
     * @return
     */
    @Bean
    public Queue stockSuccessQueue() {
        return new Queue("stockSuccessQueue", false, false, true);
    }

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

    @Bean
    public Queue directExchangeQueue() {
        return new Queue("directExchangeQueue", false, false, true);
    }

    /**
     * A direct exchange delivers messages to queues based on the message routing key.
     *
     * @return
     */
    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange("directExchange", false, true);
    }

    @Bean
    public Binding directExchangeBinding(){
        return BindingBuilder.bind(directExchangeQueue()).to(directExchange()).with("directExchangeRoutingKey");
    }

    /**
     * Topic exchanges route messages to one or many queues based on matching between a message routing key and
     * the pattern that was used to bind a queue to an exchange.
     *
     * @return
     */
    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange("topicExchange", false, true);
    }

    /**
     * A fanout exchange routes messages to all of the queues that are bound to it and the routing key is ignored.
     * If N queues are bound to a fanout exchange, when a new message is published to that exchange
     * a copy of the message is delivered to all N queues. Fanout exchanges are ideal for the broadcast routing of messages.
     *
     * @return
     */
    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange("fanoutExchange", false, true);
    }

    /**
     * A headers exchange is designed for routing on multiple attributes that are more easily expressed as message headers than a routing key.
     * Headers exchanges ignore the routing key attribute. Instead, the attributes used for routing are taken from the headers attribute.
     *
     * @return
     */
    @Bean
    public HeadersExchange headersExchange() {
        return new HeadersExchange("headersExchange", false, true);
    }

}
