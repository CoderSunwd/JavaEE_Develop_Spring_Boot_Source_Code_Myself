package com.wisely.highlight_spring4.ch2.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/*
 * 事件发布类
 */
@Component
public class DemoPublisher {

    // 注入 ApplicationContext 用来发布事件。
    @Autowired
    ApplicationContext applicationContext;
    
    public void publish(String msg) {
        // 使用 ApplicationContext 的 publishEvent 方法来发布。
        applicationContext.publishEvent(new DemoEvent(this, msg));
    }
}
