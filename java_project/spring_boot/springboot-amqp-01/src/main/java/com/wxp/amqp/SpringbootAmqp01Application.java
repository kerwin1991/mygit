package com.wxp.amqp;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableRabbit // 开启基于注解的RabbitMq
@SpringBootApplication
public class SpringbootAmqp01Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootAmqp01Application.class, args);
	}
}

/**
 * 自动配置类 RabbitAutoConfiguration
 * 自动配置连接工厂： CachingConnectionFactory
 * 发送接受消息： RabbitTemplate
 * 封装rabbitmq配置： RabbitProperties
 * rabbitmq系统管理组件： AmqpAdmin 可以帮我们申明 交换器 队列
 * @EnableRabbit + @RabbitListener 配合监听消息队列里的消息
 *
 *
 * */