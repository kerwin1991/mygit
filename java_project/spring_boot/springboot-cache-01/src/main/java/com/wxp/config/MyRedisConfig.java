package com.wxp.config;

import com.wxp.pojo.Customer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

import java.net.UnknownHostException;

/**
 *
 */
@Configuration
public class MyRedisConfig {

    @Bean
    public RedisTemplate<Object, Customer> custRedisTemplate(
            RedisConnectionFactory redisConnectionFactory)
            throws UnknownHostException {
        RedisTemplate<Object, Customer> template = new RedisTemplate<Object, Customer>();
        template.setConnectionFactory(redisConnectionFactory);
        // 将默认的jdk序列化器，转变为json的序列化转换器
        Jackson2JsonRedisSerializer<Customer> serializer = new Jackson2JsonRedisSerializer<Customer>(Customer.class);
        template.setDefaultSerializer(serializer);
        return template;
    }


}
