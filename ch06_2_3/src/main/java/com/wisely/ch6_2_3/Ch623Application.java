package com.wisely.ch6_2_3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wisely.ch6_2_3.config.AuthorSettings;

@RestController
@SpringBootApplication
public class Ch623Application {

    // 可以用 @Autowired 直接注入该配置。
    @Autowired
    private AuthorSettings authorSettings;
    
    @RequestMapping("/")
    public String index() {
        return "author name is " + authorSettings.getName() + " and author age is " + authorSettings.getAge();
    }
    
	public static void main(String[] args) {
		SpringApplication.run(Ch623Application.class, args);
	}
}
