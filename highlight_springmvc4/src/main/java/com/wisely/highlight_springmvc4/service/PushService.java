package com.wisely.highlight_springmvc4.service;


import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.async.DeferredResult;

@Service
public class PushService {

    private DeferredResult<String> deferredResult;
    
    public DeferredResult<String> getAsyncUpdate(){
        deferredResult = new DeferredResult<String>();
        return deferredResult;
    }
    
    // 在 PushService 里产生 DeferredResult 给控制器使用，通过@Scheduled 注解的方法定时更新DeferredResult
    // deferredResult有值才会调用返回方法
    @Scheduled(fixedDelay = 5000)
    public void refresh() {
        if (deferredResult != null) {
            deferredResult.setResult(new Long(System.currentTimeMillis()).toString());
        }
    }

}
