package com.wisely.highlight_spring4.ch1.aop;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan("com.wisely.highlight_spring4.ch1.aop")
// 使用 @EnableAspectJAutoProxy 注解开启 Spring 对 AspectJ 代理的支持。
@EnableAspectJAutoProxy
public class AopConfig {

}
