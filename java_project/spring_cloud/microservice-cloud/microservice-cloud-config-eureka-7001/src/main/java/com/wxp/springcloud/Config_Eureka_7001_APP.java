package com.wxp.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * Eureka Server
 */
@SpringBootApplication
@EnableEurekaServer // 表明是ereka服务端 EurekaServer 服务端启动类，接受其它微服务注册进来
public class Config_Eureka_7001_APP {
    public static void main(String[] args) {
        SpringApplication.run(Config_Eureka_7001_APP.class, args);
    }
}