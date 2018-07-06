package com.wisely.highlight_springmvc4;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

/*
 * WebApplicationInitializer 是 Spring 提供用来配置 Servlet3.0+配置的接口，从而实现类替代 web.xml 的位置。
 * 实现此接口将会自动被 SpringServletContainerInitializer（用来启动 Servlet 3.0容器）获取到。
 */
public class WebInitializer implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        // 新建 WebApplicationContext，注册配置类，并将其和当前 servletContext 关联。
        AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
        ctx.register(MyMvcConfig.class);
        ctx.setServletContext(servletContext);
        
        // 注册 Spring MVC 的  DispatcherServlet。
        Dynamic servlet = servletContext.addServlet("dispatcher", new DispatcherServlet(ctx));
        servlet.addMapping("/");
        servlet.setLoadOnStartup(1);
        // 开启异步方法支持
        servlet.setAsyncSupported(true);
    }
    
}
