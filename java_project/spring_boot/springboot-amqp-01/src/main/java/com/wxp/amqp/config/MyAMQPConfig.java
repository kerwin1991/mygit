package com.wxp.amqp.config;

import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 */
@Configuration
public class MyAMQPConfig {


    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    // 自动配置类中，如果容器中已经有了 MessageConverter 会给 RabbitTemplate 设置进去
    // RabbitAutoConfiguration -- rabbitTemplate()

}
