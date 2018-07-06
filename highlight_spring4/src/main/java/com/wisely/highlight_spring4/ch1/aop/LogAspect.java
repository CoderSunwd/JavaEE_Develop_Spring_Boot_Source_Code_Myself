package com.wisely.highlight_spring4.ch1.aop;

import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

// 通过 @Aspect 注解声明一个切面。
@Aspect
// 通过 @Component 让此切面称谓 Spring 容器管理的 Bean。
@Component
public class LogAspect {

    // 通过 @PointCut 注解声明切点。
    @Pointcut("@annotation(com.wisely.highlight_spring4.ch1.aop.Action)")
    public void annotationPointCut() {}
    
    // 通过 @After 注解声明一个建言，并使用 @PointCut 定义的切点。
    @After("annotationPointCut()")
    public void after(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature)joinPoint.getSignature();
        Method method = signature.getMethod();
        Action action = method.getAnnotation(Action.class);
        // 通过反射可获得注解上的属性，然后做日志记录相关的操作，下面的相同。
        System.out.println("注解式拦截 " + action.name());
    }
    
    // 通过 @Before 注解声明一个建言，此建言直接使用拦截规则作为参数。
    @Before("execution(* com.wisely.highlight_spring4.ch1.aop.DemoMethodService.*(..))")
    public void before(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature)joinPoint.getSignature();
        Method method = signature.getMethod();
        System.out.println("方法规则式拦截 " + method.getName());
    }
}
