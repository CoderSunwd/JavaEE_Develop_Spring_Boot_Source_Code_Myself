package com.wisely.ch8_5;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
// 在Spring Boot中还是要使用 @EnableCaching 开启缓存支持
@EnableCaching
public class Ch85Application {

	public static void main(String[] args) {
		SpringApplication.run(Ch85Application.class, args);
	}
}
