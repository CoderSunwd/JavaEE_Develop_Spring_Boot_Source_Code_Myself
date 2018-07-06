package com.wisely.highlight_spring4.ch2.prepost;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.wisely.highlight_spring4.ch2.prepost")
public class PrePostConfig {

    // initMethod 和 destroyMethod 指定 BeanWayService 类的 init 和 destroy 方法在构造之后、 Bean 销毁之前执行。
    @Bean(initMethod="init", destroyMethod="destroy")
    BeanWayService beanWayService() {
        return new BeanWayService();
    }
    
    @Bean
    JSR250WayService jsr250WayService() {
        return new JSR250WayService();
    }
}
