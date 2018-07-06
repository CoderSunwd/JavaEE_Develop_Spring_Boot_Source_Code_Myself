package com.wisely.ch9_3_5;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/*
 * 使用 @RabbitListener 来监听 RabbitMQ 的目的地发送的消息，通过 queues 属性指定要监听的目的地。
 */

@Component
public class Receiver {

    @RabbitListener(queues = "my-queue")
    public void receiveMessage(String message) {
        System.out.println("Received <" + message + ">");
    }
}
