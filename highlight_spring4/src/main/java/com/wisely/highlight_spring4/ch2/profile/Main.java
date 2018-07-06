package com.wisely.highlight_spring4.ch2.profile;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        
        // 先将活动的 Profile 设置为 prod。
        context.getEnvironment().setActiveProfiles("prod");
        // 后置注册 Bean 配置类，不然会报 Bean 未定义的错误。
        context.register(ProfileConfig.class);
        // 刷新容器。
        context.refresh();
        
        DemoBean demoBean = context.getBean(DemoBean.class);
        
        System.out.println(demoBean.getContent());
        
        context.close();
    }
}
