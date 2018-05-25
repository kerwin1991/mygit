package com.wxp.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 *
 */
@SpringBootApplication
@EnableEurekaClient // Ribbon 是基于eureka的 负载均衡 属于eureka客户端
// Feign支持
@EnableFeignClients(basePackages = {"com.wxp.springcloud"})
@ComponentScan("com.wxp.springcloud") // 需要扫描到 api 包下的 DeptClientService 类 @FeignClient 注解
public class ConsumerDept_80_Feign_APP {
    public static void main(String[] args) {
        SpringApplication.run(ConsumerDept_80_Feign_APP.class, args);
    }
}
