package com.wisely.ch7_6;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

@Configuration
// 通过@EnableWebSocketMessageBroker 注解开启使用 STOMP 协议来传输基于代理（message broker）的消息，
// 这时控制器支持使用 @MessageMapping，就像使用@RequestMapping一样。
@EnableWebSocketMessageBroker
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer {

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) { // 注册 STOMP 协议的节点（endpoint），并映射的指定的 URL。
        registry.addEndpoint("/endpointWisely").withSockJS(); // 注册一个 STOMP 的 endpoint，并指定使用SockJS协议。
        registry.addEndpoint("/endpointChat").withSockJS(); // 注册一个名为 /endpointChat 的 endpoint。
    }
    
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) { // 配置消息代理（Message Broker）。
        registry.enableSimpleBroker("/queue", "/topic"); // 广播式应配置一个 /topic 消息代理。
    }
}
