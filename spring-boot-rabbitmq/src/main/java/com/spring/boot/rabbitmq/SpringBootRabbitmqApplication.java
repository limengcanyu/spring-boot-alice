package com.spring.boot.rabbitmq;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

@SpringBootApplication
public class SpringBootRabbitmqApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(SpringBootRabbitmqApplication.class, args);
//        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
//        for (String name : beanDefinitionNames) {
//            System.out.println(name);
//        }
    }

    /**
     * 接收消息，无响应消息
     *
     * @param content
     * @return
     */
    @RabbitListener(queues = "myqueue")
    public void receive(String content) {
        System.out.println("myqueue receive message: " + content);
    }

    /**
     * 接收消息，返回响应消息
     *
     * @param content
     * @return
     */
    @RabbitListener(queues = "sendAndReceiveQueue")
    public String receiveAndReplay(String content) {
        System.out.println("sendAndReceiveQueue receive message: " + content);

        // test convertSendAndReceive timeout
//        try {
//            Thread.sleep(10000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        return "replay response";
    }
}
