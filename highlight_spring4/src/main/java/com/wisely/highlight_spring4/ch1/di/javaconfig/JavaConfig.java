package com.wisely.highlight_spring4.ch1.di.javaconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JavaConfig {
    @Bean
    public FunctionService functionService() {
        return new FunctionService();
    }
    
    @Bean
    public UseFunctionService useFunctionService() {
        UseFunctionService useFunctionService = new UseFunctionService();
        //给useFunctionService 这个Bean注入functionService()方法返回的Bean 
        useFunctionService .setFunctionService(functionService());
        return useFunctionService;
    }
}
