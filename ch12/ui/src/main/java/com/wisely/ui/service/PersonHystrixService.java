package com.wisely.ui.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.wisely.ui.domain.Person;

/*
 * 调用 Person Service 的断路器 
 */
@Service
public class PersonHystrixService implements PersonService {

    @Autowired
    PersonService personService;
    
    // 使用 @HystrixCommand 的 fallbackMethod 参数指定，当本方法调用失败时，调用后备方法 fallbackService。
    @HystrixCommand(fallbackMethod = "fallbackSave")
    public List<Person> save(String name) {
        return personService.save(name);
    }
    
	public List<Person> fallbackSave(String name){ 
		List<Person> list = new ArrayList<>();
		Person p = new Person(name+"没有保存成功，Person Service 故障");
		list.add(p);
		return list;
	}
}
