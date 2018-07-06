package com.wisely.highlight_spring4.ch2.prepost;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/*
 * 使用 JSR250形式的 Bean。
 */
public class JSR250WayService {

    // @PostConstruct，在构造函数执行之后执行。
    @PostConstruct
    public void init() {
        System.out.println("jsr250-init-method");
    }
    
    public JSR250WayService() {
        super();
        System.out.println("初始化构造函数-JSR250WayService");
    }
    
    // @PreDestroy，在 Bean 销毁之前执行。
    @PreDestroy
    public void destroy() {
        System.out.println("jsr250-destory-method");
    }
}
