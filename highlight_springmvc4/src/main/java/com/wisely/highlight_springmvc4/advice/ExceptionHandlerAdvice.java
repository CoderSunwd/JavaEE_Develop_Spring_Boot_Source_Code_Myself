package com.wisely.highlight_springmvc4.advice;

import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

// @ControllerAdvice 声明一个控制器建言，其组合了 @Component 注解，所以自动注册为 Spring 的 Bean。
@ControllerAdvice
public class ExceptionHandlerAdvice {

    // @ExceptionHandler 在此处定义全局处理，通过 @ExceptionHandler 的 value 属性克过滤拦截的条件，在此处我们可以看出我们拦截所有的 Exception。
    @ExceptionHandler(value = Exception.class)
    public ModelAndView exception(Exception exception, WebRequest request) {
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("errorMessage", exception.getMessage());
        return modelAndView;
    }
    
    // 此处使用 @ModelAttribute 注解将键值对添加到全局，所有注解的 @RequestMapping 的方法可获得此键值对。
    @ModelAttribute
    public void addAttribute(Model model) {
        model.addAttribute("msg", "额外信息");
    }
    
    // 通过 @InitBinder 注解定制 WebDataBinder。
//    @InitBinder
//    public void initBinder(WebDataBinder webDataBinder) {
//        // 此处演示忽略 request 参数的 id，更多关于 WebDataBinder 的配置，请参考 WebDataBinder 的 API 文档。
//        webDataBinder.setDisallowedFields("id");
//    }
}
