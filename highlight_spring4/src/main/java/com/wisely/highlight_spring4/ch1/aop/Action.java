package com.wisely.highlight_spring4.ch1.aop;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// @Target 用来说明该注解可以被声明在那些元素之前。
// ElementType.METHOD：说明该注解只能被声明在一个类的方法前。
@Target(ElementType.METHOD)
// @Retention 用来说明该注解类的生命周期
// RetentionPolicy.RUNTIME  : 注解保留在程序运行期间，此时可以通过反射获得定义在某个类上的所有注解。
@Retention(RetentionPolicy.RUNTIME) 
@Documented

// 使用@interface自定义注解
// 定义注解格式：
// public @interface 注解名 {定义体}
public @interface Action {
    String name();
}
