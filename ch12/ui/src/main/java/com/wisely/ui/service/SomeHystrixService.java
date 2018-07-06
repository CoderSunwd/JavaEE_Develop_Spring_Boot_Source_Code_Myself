package com.wisely.ui.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

/*
 * 使用 ribbon 调用 Some Service，并使用断路器
 */

@Service
public class SomeHystrixService {

    // 在 Spring Boot 下使用Ribbon，我们只需注入一个 RestTemplate 即可， Spring Boot 已为我们做好了配置。
    @Autowired
    RestTemplate restTemplate;
    
    // 使用 @HystrixCommand 的 fallbackMethod 参数指定，当方法滴啊用失败时调用后备方法 fallbackSome。
    @HystrixCommand(fallbackMethod = "fallbackSome")
    public String getSome() {
        return restTemplate.getForObject("http://localhost/some/getsome", String.class);
    }
    
    public String fallbackSome() {
        return "some service 模块故障";
    }
}
