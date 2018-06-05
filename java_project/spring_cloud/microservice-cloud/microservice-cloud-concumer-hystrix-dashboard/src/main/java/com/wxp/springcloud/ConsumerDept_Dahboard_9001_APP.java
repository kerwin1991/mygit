package com.wxp.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

/**
 * EnableHystrixDashboard 开启仪表盘监控
 */
@SpringBootApplication
@EnableHystrixDashboard
public class ConsumerDept_Dahboard_9001_APP {

    public static void main(String[] args) {
        SpringApplication.run(ConsumerDept_Dahboard_9001_APP.class, args);
    }
}

/**
 * 首页地址
 * http://localhost:9001/hystrix
 *
 *
 * */
