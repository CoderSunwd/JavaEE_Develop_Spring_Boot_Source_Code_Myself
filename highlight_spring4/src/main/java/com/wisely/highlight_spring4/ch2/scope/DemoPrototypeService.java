package com.wisely.highlight_spring4.ch2.scope;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
// 声明 Scope 为 Prototype。
@Scope("prototype")
public class DemoPrototypeService {

}
