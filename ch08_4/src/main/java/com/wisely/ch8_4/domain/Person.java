package com.wisely.ch8_4.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

// @Entity 注解指明这是一个和数据库表映射的实体类
@Entity
public class Person {

    // @Id 注解指明这个属性映射为数据库的主键
    @Id
    // @GeneratedValue 注解默认使用主键生成方式为自增，hibernate会为我们自动生成一个名为 HIBERNATE_SEQUENCE 的序列
    @GeneratedValue
    private Long id;
    
    private String name;
    
    private Integer age;
    
    private String address;

    public Person() {
        super();
    }

    public Person(Long id, String name, Integer age, String address) {
        super();
        this.id = id;
        this.name = name;
        this.age = age;
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    
}
