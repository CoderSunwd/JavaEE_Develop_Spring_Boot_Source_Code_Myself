package com.wisely.ch9_4;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.channel.MessageChannels;
import org.springframework.integration.dsl.core.Pollers;
import org.springframework.integration.dsl.file.Files;
import org.springframework.integration.dsl.mail.Mail;
import org.springframework.integration.feed.inbound.FeedEntryMessageSource;
import org.springframework.integration.file.support.FileExistsMode;
import org.springframework.integration.scheduling.PollerMetadata;

import com.rometools.rome.feed.synd.SyndEntry;

@SpringBootApplication
public class Ch94Application {
    
    public static void main(String[] args) {
        SpringApplication.run(Ch94Application.class, args);
    }

    /*
     * 读取流程
     */
    // 通过 @Value 注解自动获得 https://spring.io/blog.atom 的资源。
    @Value("https://spring.io/blog.atom")
    Resource resource;
    
    @Bean(name = PollerMetadata.DEFAULT_POLLER)
    public PollerMetadata poller() { // 使用 Fluent API 和 Pollers 配置默认的轮询方式。
        return Pollers.fixedRate(500).get();
    }
    
    // 使用 FeedEntryMessageSource 实际为 feed:inbound-channel-adapter，此处即构造feed的入站通道适配器作为数据输入。
    @Bean
    public FeedEntryMessageSource feedMessageSource() throws IOException {
        FeedEntryMessageSource messageSource = new FeedEntryMessageSource(resource.getURL(), "news");
        return messageSource;
    }
    
    @Bean
    public IntegrationFlow myFlow() throws IOException {
        return IntegrationFlows.from(feedMessageSource()) // 流程从from方法开始。
                // 通过路由方法route来选择路由，消息体（payload）的类型为SyndEntry，作为判断条件的类型为String，
                // 判断的值是通过payload获得的分类（Categroy）。
                .<SyndEntry, String> route(payload -> 
                payload.getCategories().get(0).getName(), 
                // 通过不同分类的值转向不同的消息通道，若分类为releases，则转向releasesChannel；
                // 若分类为engineering，则转向engineeringChannel；若分类为news，则转向newsChannel。
                mapping -> mapping.channelMapping("releases", "releasesChannel")
                .channelMapping("engineering", "engineeringChannel")
                .channelMapping("news", "newsChannel"))
                // 通过get方法获得IntegrationFlow实体，配置为Spring的Bean。
                .get();
    }
    
    /*
     * releases流程
     */
    @Bean
    public IntegrationFlow releasesFlow() {
        // 从消息通道 releasesChannel 开始获取数据
        return IntegrationFlows.from(MessageChannels.queue("releasesChannel", 10))
                .<SyndEntry, String> transform(
                        payload -> " 《" + payload.getTitle() + "》  "
                        // 使用 transform 方法进行数据转换。payload 类型为 SyndEntry，将其转换为字符串类型，并自定义数据的格式。
                        + payload.getLink() + System.getProperty("line.separator"))
                // 用 handle 方法处理 file 的出站适配器。Files 类是由 Spring Integration Java DSL 提供的 Fluent API 用来构造文件输出的适配器。
                .handle(Files.outboundAdapter(new File("e:/springblog"))
                        .fileExistsMode(FileExistsMode.APPEND)
                        .charset("UTF-8")
                        .fileNameGenerator(message -> "releases.txt")
                        .get())
                .get();
    }
    
    /*
     * engineering流程
     */
    @Bean
    public IntegrationFlow engineeringFlow() {
        return IntegrationFlows.from(MessageChannels.queue("engineeringChannel", 10))
                .<SyndEntry, String> transform(
                        e -> " 《" + e.getTitle() + "》 " + e.getLink()
                        + System.getProperty("line.separator"))
                .handle(Files.outboundAdapter(new File("e:/springblog"))
                        .fileExistsMode(FileExistsMode.APPEND)
                        .charset("UTF-8")
                        .fileNameGenerator(message -> "engineering.txt")
                        .get())
                .get();
    }
    
    /*
     * news流程
     */
    @Bean
    public IntegrationFlow newsFlow() {
        return IntegrationFlows.from(MessageChannels.queue("newsChannel", 10))
                .<SyndEntry, String> transform(
                        payload -> " 《" + payload.getTitle() + "》 "
                        + payload.getLink() + System.getProperty("line.separator"))
                // 通过enricherHeader来增加消息头的信息。
                .enrichHeaders(
                        // 邮件发送的相关消息通过 Spring Integration Java DSL 提供的 Mail 的 headers 方法来构造。
                        Mail.headers()
                        .subject("来自Spring的新闻")
                        .to("sunwd@yxb.com")
                        .from("sunwd@yxb.com"))
                // 使用 handle 方法来定义邮件发送的出站适配器，使用 Spring Integration Java DSL 提供的 Mail.outboundAdapter 来构造，
                // 这里使用 wisely-man@126.com 邮箱向自己发送邮件。
                .handle(Mail.outboundAdapter("smtp.ym.163.com")
                        .port(25)
                        .protocol("smtp")
                        .credentials("sunwd@yxb.com", "111111")
                        .javaMailProperties(p -> p.put("mail.debug", "false")),
                        e -> e.id("smtpOut"))
                .get();
    }
    
}
