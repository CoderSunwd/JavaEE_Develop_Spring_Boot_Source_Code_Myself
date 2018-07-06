package com.wisely.highlight_spring4.ch3.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/*
 * 示例组合注解
 */

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
// 组合 @Configuration 元注解。
@Configuration
// 组合 @ComponentScan 元注解。
@ComponentScan
public @interface WiselyConfiguration {
    String[] value() default {}; // 覆盖 value 参数。
}
