package com.wxp.springcloud;

import com.wxp.rule.MySelfRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

/**
 * @RibbonClient 针对 哪个 微服务 使用 哪个 自定义的负载均衡 算法/规则
 */
@SpringBootApplication
@EnableEurekaClient // Ribbon 是基于eureka的 负载均衡 属于eureka客户端
// 在启动该微服务的时候就能去加载我们的自定义Ribbon配置类，从而使配置生效
@RibbonClient(name = "MICROSERVICE-CLOUD-DEPT", configuration = MySelfRule.class)
public class ConsumerDept_80_APP {
    public static void main(String[] args) {
        SpringApplication.run(ConsumerDept_80_APP.class, args);
    }
}
