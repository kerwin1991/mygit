package com.wxp.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 *
 */
@SpringBootApplication
@EnableEurekaClient // Ribbon 是基于eureka的 负载均衡 属于eureka客户端
public class ConsumerDept_80_APP {
    public static void main(String[] args) {
        SpringApplication.run(ConsumerDept_80_APP.class, args);
    }
}
