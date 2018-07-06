package com.wisely.highlight_spring4.ch3.fortest;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

// SpringJUnit4ClassRunner 在 JUnit 环境下提供 Spring TestContext Framework 的功能。
@RunWith(SpringJUnit4ClassRunner.class)
// @ContextConfiguration 用来加载配置 ApplicationContext，其中 classes 属性用来加载配置类。
@ContextConfiguration(classes = {TestConfig.class})
// @ActiveProfiles 用来声明活动的 profile。
@ActiveProfiles("dev")
public class DemoBeanIntegrationTests {

    // 可使用普通的 @Autowired 注入 Bean。
    @Autowired
    private TestBean testBean;
    
    // 测试代码，通过 JUnit 的 Assert 来校验结果是否和预期一致。
    @Test
    public void prodBeanShouldInject() {
        String expected = "from production profile";
        String actual = testBean.getContent();
        Assert.assertEquals(expected, actual);
    }
    
}
