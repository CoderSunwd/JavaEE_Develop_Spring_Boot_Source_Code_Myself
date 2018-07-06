package com.wisely.highlight_spring4.ch3.taskexecutor;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/*
 * 任务执行类
 */
@Service
public class AsyncTaskService {

    // 通过 @Async 注解表明该方法是个异步方法，如果注解在类级别，则表明所有的方法都是异步的，
    // 而这里的方法自动被注入使用 ThreadPoolTaskExecutor 作为 TaskExecutor。
    @Async
    public void executeAsyncTask(Integer i) {
        System.out.println("执行异步任务：" + i);
    }
    
    @Async
    public void executeAsyncTaskPlus(Integer i) {
        System.out.println("执行异步任务+1：" + (i + 1));
    }
}
