package com.wxp.config;

import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * Created by Administrator on 2018/5/17.
 */
@Configuration
public class MyCacheConfig {

    // 自定义缓存key生成策略
    @Bean("myKeyGenerator")
    public KeyGenerator keyGenerator() {
        return new KeyGenerator(){
            @Override
            public Object generate(Object target, Method method, Object... params) {
                return method.getName() + "<" + Arrays.asList(params).toString() + ">";
            }
        };
    }

}
