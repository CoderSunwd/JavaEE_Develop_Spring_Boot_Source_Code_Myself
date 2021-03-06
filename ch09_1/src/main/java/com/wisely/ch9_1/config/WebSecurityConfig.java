package com.wisely.ch9_1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.wisely.ch9_1.security.CustomUserService;

@Configuration
// 扩展 Spring Security 配置需继承 WebSecurityConfigurerAdapter.
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    // 注册 CustomUserService 的 Bean
    UserDetailsService customUserService() {
        return new CustomUserService();
    }
    
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 添加我们自定义的 user detail service 认证
        auth.userDetailsService(customUserService());
    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
            .anyRequest().authenticated() // 所有请求需要认证即登录后才能访问。
            .and()
            .formLogin()
            .loginPage("/login")
            .failureUrl("/login?error")
            .permitAll() // 定制登录行为，登录页面可任意访问。
            .and()
            .logout().permitAll(); // 定制注销行为，注销请求可任意访问。
    }
}
