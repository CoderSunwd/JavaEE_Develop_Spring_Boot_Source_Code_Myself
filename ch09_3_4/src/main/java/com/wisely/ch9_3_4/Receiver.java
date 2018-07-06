package com.wisely.ch9_3_4;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class Receiver {

    // JmsListener 是 Spring 4.1 为我们提供的一个新特性，用来简化 JMS 开发。我们只需在这个注解的属性 destination 指定要监听的目的地，
    // 即可接收该目的地发送的消息。此例监听 my-destination 目的地发送的消息。
    @JmsListener(destination = "my-destination")
    public void receiveMessage(String message) {
        System.out.println("接受到：<" + message + ">");
    }
}
