package com.wisely.highlight_spring4.ch2.el;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;

@Configuration
@ComponentScan("com.wisely.highlight_spring4.ch2.el")
@PropertySource("classpath:com/wisely/highlight_spring4/ch2/el/test.properties")
public class ElConfig {

    // 1、注入普通字符串。
    @Value("I Love You!")
    private String normal;
    
    // 2、注入操作系统属性。
    @Value("#{systemProperties['os.name']}")
    private String osName;
    
    // 3、注入表达式结果。
    @Value("#{ T(java.lang.Math).random() * 100.0 }")
    private double randomNumber;
    
    // 4、注入其他 Bean 属性。
    @Value("#{demoService.another}")
    private String fromAnother;
    
    // 5、注入文件资源。
    @Value("classpath:com/wisely/highlight_spring4/ch2/el/test.txt")
    private Resource testFile;
    
    // 6、注入网址资源。
    @Value("http://www.baidu.com")
    private Resource testUrl;
    
    // 7、注入配置文件。
    @Value("${book.name}")
    private String bookName;
    
    // 7、注入配置文件。
    // 注入 Properties 还可从 Environment 中获得。
    @Autowired
    private Environment environment;
    
    // 7、注入配置文件。
    // 注入配置文件需使用 @PropertySource 指定文件地址，若使用 @Value 注入，则要配置一个 PropertySourcesPlaceholderConfigurer 的 Bean。
    // 注意， @Value("${book.name}")使用的是 "$"，而不是"#"。
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfigure() {
        return new PropertySourcesPlaceholderConfigurer();
    }
    
    public void outputResource() {
        try {
            System.out.println(normal);
            System.out.println(osName);
            System.out.println(randomNumber);
            System.out.println(fromAnother);
            System.out.println(IOUtils.toString(testFile.getInputStream()));
            System.out.println(bookName);
            System.out.println(environment.getProperty("book.author"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
        
}
