package com.wisely.ch5_2_2;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
// @SpringBootApplication 是 Spring Boot 项目的核心注解，主要目的是开启自动配置。
// 关闭特定的自动配置应该使用 @SpringBootApplication 注解的 exclude 参数。
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class Ch522Application {

    @Value("${book.author}")
    private String bookAuthor;
    @Value("${book.name}")
    private String bookName;
    
    @RequestMapping("/")
    String index() {
        return "Hello Spring Boot! book name is:" + bookName + " and book author is:" + bookAuthor;
    }
    
    // 这是一个标准的 Java 应用的 main 方法，主要作用是作为项目的启动的入口。
	public static void main(String[] args) {
//		SpringApplication.run(Ch522Application.class, args);
	    SpringApplication app = new SpringApplication(Ch522Application.class);
	    app.setBannerMode(Banner.Mode.OFF);
	    app.run(args);
	}
}
