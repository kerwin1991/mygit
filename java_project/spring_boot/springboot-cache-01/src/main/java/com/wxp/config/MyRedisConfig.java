package com.wxp.config;

import com.wxp.pojo.Customer;
import com.wxp.pojo.User;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.RedisCacheManager;
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

    // 自定义CacheManager 修改了 序列化机制jdk为json
    // CacheManagerCustomizers 定制缓存的一些规则
    // key 多了前缀
    @Bean
    public RedisCacheManager customerRedisCacheManager(RedisTemplate<Object, Customer> custRedisTemplate) {
        RedisCacheManager cacheManager = new RedisCacheManager(custRedisTemplate);
        // 因为在此设置了使用前缀，将CacheName，作为key的前缀
        cacheManager.setUsePrefix(true);
        return cacheManager;
    }

    /**
     * 当定义多个CacheManager时：需要指定一个为主CacheManager，否则报错 。可以使用注解 @Primary
     *  No CacheResolver specified, and no unique bean of type CacheManager found.
     *  Mark one as primary (or give it the name 'cacheManager') or declare a specific CacheManager to use,
     */


    @Bean
    public RedisTemplate<Object, User> userRedisTemplate(
            RedisConnectionFactory redisConnectionFactory)
            throws UnknownHostException {
        RedisTemplate<Object, User> template = new RedisTemplate<Object, User>();
        template.setConnectionFactory(redisConnectionFactory);
        Jackson2JsonRedisSerializer<User> serializer = new Jackson2JsonRedisSerializer<>(User.class);
        template.setDefaultSerializer(serializer);
        return template;
    }

    // 自定义CacheManager 修改了 序列化机制jdk为json
    // CacheManagerCustomizers 定制缓存的一些规则
    // key 多了前缀
    @Primary  // 将某个缓存管理器作为默认的，其实开发中应该使用一个Object对象的缓存管理器写在这里
    @Bean
    public RedisCacheManager userRedisCacheManager(RedisTemplate<Object, User> userRedisTemplate) {
        RedisCacheManager cacheManager = new RedisCacheManager(userRedisTemplate);
        // 因为在此设置了使用前缀，将CacheName，作为key的前缀
        cacheManager.setUsePrefix(true);
        return cacheManager;
    }

}
