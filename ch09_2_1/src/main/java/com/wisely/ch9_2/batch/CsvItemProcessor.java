package com.wisely.ch9_2.batch;

import org.springframework.batch.item.validator.ValidatingItemProcessor;
import org.springframework.batch.item.validator.ValidationException;

import com.wisely.ch9_2.domain.Person;

public class CsvItemProcessor extends ValidatingItemProcessor<Person> {
    
    @Override
    public Person process(Person item) throws ValidationException {
        super.process(item); // 需执行 super.process:(item) 才会调用自定义校验器。
        
        if(item.getNation().equals("汉族")) { // 对数据做简单的处理，若民族为汉族，则数据转换成01，其余转换成02。
            item.setNation("01");
        } else {
            item.setNation("02");
        }
        return item;
    }
}
