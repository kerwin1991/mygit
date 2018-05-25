package com.wxp.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * Created by Administrator on 2018/5/20.
 */
@SpringBootApplication
@EnableEurekaClient // 本服务启动后会自动注册进eureka服务中
@EnableDiscoveryClient // 服务发现
@EnableCircuitBreaker // 对Hystrix熔断机制的支持
public class ProviderDept_Hystrix_8001_APP {

    public static void main(String[] args) {
        SpringApplication.run(ProviderDept_Hystrix_8001_APP.class, args);
    }
}
