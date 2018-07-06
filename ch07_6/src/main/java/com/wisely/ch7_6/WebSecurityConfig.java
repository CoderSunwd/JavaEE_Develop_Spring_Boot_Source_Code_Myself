package com.wisely.ch7_6;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http
            .authorizeRequests()
            .antMatchers("/", "/login").permitAll() // 设置Spring Security 对/和/"login" 路径不拦截。
            .anyRequest().authenticated()
            .and()
            .formLogin()
            .loginPage("/login") // 设置Spring Security 的登录页面访问的路径为/login。
            .defaultSuccessUrl("/chat") // 登陆成功后转向 /chat路径。
            .permitAll()
            .and()
            .logout()
            .permitAll();
    }
    
    // 在内存中分别配置两个用户 wyf 和 wisely ，密码和用户名一致，角色是 USER。
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .inMemoryAuthentication()
            .withUser("wyf").password("wyf").roles("USER")
            .and()
            .withUser("wisely").password("wisely").roles("USER");
    }
    
    // /resources/static/ 目录下的静态资源， Spring Security 不拦截。
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/resources/static/**");
    }
}
