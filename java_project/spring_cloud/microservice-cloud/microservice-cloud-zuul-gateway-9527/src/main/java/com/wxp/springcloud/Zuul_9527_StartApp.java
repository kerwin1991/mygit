package com.wxp.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * Created by Administrator on 2018/6/4.
 */
@SpringBootApplication
@EnableZuulProxy
public class Zuul_9527_StartApp {

    public static void main(String[] args) {
        SpringApplication.run(Zuul_9527_StartApp.class, args);
    }

    /**
     * 网关地址/微服务名称/requestMapping
     * 访问地址： http://myzuul.com:9527/microservice-cloud-dept/dept/get/4
     */

}
