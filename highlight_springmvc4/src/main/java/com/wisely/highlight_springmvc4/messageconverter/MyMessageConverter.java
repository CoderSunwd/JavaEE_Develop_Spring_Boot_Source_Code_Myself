package com.wisely.highlight_springmvc4.messageconverter;

import java.io.IOException;
import java.nio.charset.Charset;

import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.util.StreamUtils;

import com.wisely.highlight_springmvc4.domain.DemoObj;

/**
 * 继承AbstractHttpMessageConverter 接口来实现自定义的HttpMessageConverter
 */
public class MyMessageConverter extends AbstractHttpMessageConverter<DemoObj>{

    public MyMessageConverter() {
        // 新建一个我们自定义的媒体类型 application/x-wisely。
        super(new MediaType("application", "x-wisely", Charset.forName("UTF-8")));
    }
    
    /**
     * 重写readlntenal 方法，处理请求的数据。代码表明我们处理由“-”隔开的数据，并转成 DemoObj 的对象。
     */
    @Override
    protected DemoObj readInternal(Class<? extends DemoObj> clazz, HttpInputMessage inputMessage)
            throws IOException, HttpMessageNotReadableException {
        String temp = StreamUtils.copyToString(inputMessage.getBody(), Charset.forName("UTF-8"));
        String[] tempArr = temp.split("-");
        return new DemoObj(new Long(tempArr[0]), tempArr[1]);
    }
    
    @Override
    protected boolean supports(Class<?> clazz) {
        // 表明本HttpMessageConverter 只处理DemoObj 这个类。
        return DemoObj.class.isAssignableFrom(clazz);
    }
    
    /**
     * 重写writeInternal ，处理如何输出数据到response。此例中，我们在原样输出前面加上"hello ："。
     */
    @Override
    protected void writeInternal(DemoObj obj, HttpOutputMessage outputMessage) 
            throws IOException, HttpMessageNotWritableException {
        String out = "hello:" + obj.getId() + "-" + obj.getName();
        outputMessage.getBody().write(out.getBytes());
    }

}
