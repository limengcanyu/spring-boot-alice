package com.spring.boot.rabbitmq;

import com.spring.boot.rabbitmq.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootRabbitmqApplicationTests {

    @Autowired
    private AmqpAdmin amqpAdmin;

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void getDefaultInfo() {
        System.out.println("getExchange: " + rabbitTemplate.getExchange());
        System.out.println("getRoutingKey: " + rabbitTemplate.getRoutingKey());

        // org.springframework.amqp.support.converter.SimpleMessageConverter
        System.out.println("getMessageConverter: " + rabbitTemplate.getMessageConverter());

    }

    @Test
    public void declareQueue() {
//        amqpAdmin.deleteQueue("testqueue");

        // declare Queue
        amqpAdmin.declareQueue(new Queue("myqueue"));
        // declare Queue
        amqpAdmin.declareQueue(new Queue("sendAndReceiveQueue"));

    }

    @Test
    public void sendingMessage() {
//        amqpAdmin.deleteQueue("testqueue");
//
//        // declare Queue
//        amqpAdmin.declareQueue(new Queue("myqueue"));

        // send 2 message
        amqpTemplate.convertAndSend("myqueue", "foo1");
        amqpTemplate.convertAndSend("myqueue", "foo2");
        amqpTemplate.convertAndSend("myqueue", "foo3");
        System.out.println("send message successful");

        // receive 1 message
//        String foo = (String) amqpTemplate.receiveAndConvert("myqueue");
//        System.out.println("receive message: " + foo);

    }

    @Test
    public void sendingUser() {
        // send 2 message, message must implements Serializable
        amqpTemplate.convertAndSend("userQueue", new User(1, "rock", 1));
        amqpTemplate.convertAndSend("userQueue", new User(2, "blue", 2));
        System.out.println("send message successful");

        // receive 1 message
        User user1 = (User) amqpTemplate.receiveAndConvert("userQueue");
        System.out.println("receive message: " + user1);

    }

    @Test
    public void sendAndReceive() {
//        Object response = amqpTemplate.convertSendAndReceive("sendAndReceiveQueue", "foo1");
//        System.out.println("send message successful, response is: " + response);
    }

}
