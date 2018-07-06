package com.wisely.highlight_spring4.ch3.aware;

import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

@Service
// 实现 BeanNameAware、ResourceLoaderAware 接口，获得 Bean 名称和资源加载的服务。
public class AwareService implements BeanNameAware, ResourceLoaderAware {

    private String beanName;
    private ResourceLoader loader;
    
    @Override
    // 实现 ResourceLoaderAware 需重写 setResourceLoader。
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.loader = resourceLoader;
    }
    
    @Override
    // 实现 BeanNameAware 需重写 setBeanName。
    public void setBeanName(String name) {
        this.beanName = name;
    }
    
    public void outputResult() {
        System.out.println("Bean 的名称为：" + beanName);
        Resource resource = loader.getResource("classpath:com/wisely/highlight_spring4/ch3/aware/test.txt");
        try {
            System.out.println("ResourceLoader 加载的文件内容为：" + IOUtils.toString(resource.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
