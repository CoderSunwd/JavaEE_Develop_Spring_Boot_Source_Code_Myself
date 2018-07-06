package com.wisely.ch8_6_2.domain;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

import com.wisely.ch8_6_2.dao.Person;

@Repository
public class PersonDao {

    // Spring Boot 已为我们配置 StringRedisTemplate，在此处可以直接注入。
    @Autowired
    StringRedisTemplate stringRedisTemplate;
    
    // 可以使用 @Resource 注解指定 stringRedisTemplate，可注入基于字符串的简单属性操作方法。
    @Resource(name = "stringRedisTemplate")
    ValueOperations<String, String> valOpsStr;
    
    // Spring Boot 已为我们配置 RedisTemplate，在此处可以直接注入。
    @Autowired
    RedisTemplate<Object, Object> redisTemplate;
    
    // 可以使用 @Resource 注解指定 redisTemplate，可注入基于对象的简单属性操作方法。
    @Resource(name = "redisTemplate")
    ValueOperations<Object, Object> valOps;
    
    // 通过set方法，存储字符串类型。
    public void stringRedisTemplateDemo() {
        valOpsStr.set("xx", "yy");
    }
    
    // 通过set方法，存储对象类型。
    public void save(Person person) {
        valOps.set(person.getId(), person);
    }
    
    // 通过get方法，获得字符串。
    public String getString() {
        return valOpsStr.get("xx");
    }
    
    // 通过get方法，获得对象。
    public Person getPerson() {
        return (Person) valOps.get("1");
    }
}

