package com.wisely.ch6_2_3.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
// 通过@ConfigurationProperties 加载 properties 文件内的配置，通过 prefix 属性指定 properties 的配置的前缀，
// 通过 locations 指定 properties 文件的位置
@ConfigurationProperties(prefix = "author")
public class AuthorSettings {

    private String name;
    private Long age;
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Long getAge() {
        return age;
    }
    public void setAge(Long age) {
        this.age = age;
    }
    
}
