package com.wisely.ch11_1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
// 实现 HealthIndicator 接口并重写 health()方法
public class StatusHealth implements HealthIndicator {

    @Autowired
    StatusService statusService;
    
    @Override
    public Health health() {
        String status = statusService.getStatus();
        if (status == null || !status.equals("running")) {
            // 当 status 的值为非 running 时构造失败。
            return Health.down().withDetail("Error", "Not Running").build();
        }
        // 其余情况运行成功。
        return Health.up().build();
    }
}
