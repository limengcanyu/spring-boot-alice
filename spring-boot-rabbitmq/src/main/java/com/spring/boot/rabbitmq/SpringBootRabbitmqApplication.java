package com.spring.boot.rabbitmq;

import com.spring.boot.rabbitmq.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.util.ObjectUtils;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

@Slf4j
@SpringBootApplication
public class SpringBootRabbitmqApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(SpringBootRabbitmqApplication.class, args);
//        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
//        for (String name : beanDefinitionNames) {
//            log.debug(name);
//        }
    }

//    /**
//     * 接收消息，无响应消息
//     *
//     * @param content
//     * @return
//     */
//    @RabbitListener(queues = "myqueue")
//    public void receive(String content) {
//        log.debug("myqueue receive message: " + content);
//    }


    /**
     * 接收POJO
     *
     * @param user
     */
    @RabbitListener(queues = "userQueue")
    public void receive(User user) {
        log.debug("userQueue receive message: " + user);
    }


//    /**
//     * 接收消息，返回响应消息
//     *
//     * @param content
//     * @return
//     */
//    @RabbitListener(queues = "sendAndReceiveQueue")
//    public String receiveAndReplay(String content) {
//        log.debug("sendAndReceiveQueue receive message: " + content);
//
//        // test convertSendAndReceive timeout
////        try {
////            Thread.sleep(10000);
////        } catch (InterruptedException e) {
////            e.printStackTrace();
////        }
//
//        return "replay response";
//    }

    @RabbitListener(queues = "orderQueue")
    @SendTo("stockSuccessQueue")
    public String receiveOrder(String message) {
        log.debug("orderQueue receive message: " + message);
        return "stock Success message";
    }

    @RabbitListener(queues = "directExchangeQueue1")
    public void directExchangeQueue1(String message) {
        log.debug("directExchangeQueue1 receive message: " + message);
    }

    @RabbitListener(queues = "topicExchangeQueue1")
    public void topicExchangeQueue1(String message) {
        log.debug("topicExchangeQueue1 receive message: " + message);
    }

    @RabbitListener(queues = "fanoutExchangeQueue1")
    public void fanoutExchangeQueue1(String message) {
        log.debug("fanoutExchangeQueue1 receive message: " + message);
    }
}
