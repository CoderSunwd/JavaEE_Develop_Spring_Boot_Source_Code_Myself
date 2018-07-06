package com.wisely.highlight_spring4.ch2.profile;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class ProfileConfig {

    @Bean
    // Profile 为 dev 时实例化 devDemoBean。
    @Profile("dev")
    public DemoBean devDemoBean() {
        return new DemoBean("from devlopment profile");
    }
    
    @Bean
    // Profile 为 prod 时实例化 prodDemoBean。
    @Profile("prod")
    public DemoBean proDemoBean() {
        return new DemoBean("from production profile");
    }
}
