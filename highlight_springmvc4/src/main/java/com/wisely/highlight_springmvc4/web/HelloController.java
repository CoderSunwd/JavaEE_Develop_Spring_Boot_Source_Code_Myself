package com.wisely.highlight_springmvc4.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

// 利用 @Controller 注解声明一个控制器。
@Controller
public class HelloController {

    // 利用 @RequestMapping 配置 URL 和方法之间的映射。
    @RequestMapping("/index")
    public String hello() {
        // 通过上面 ViewResolver 的 Bean 配置，返回值为 index，说明我们的页面放置的路径为 /WEB-INF/classes/views/index.jsp。
        return "index";
    }
}
