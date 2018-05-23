package com.wxp.service;

import com.wxp.mapper.UserMapper;
import com.wxp.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.Cache;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.stereotype.Service;

/**
 * UserService
 */
@Service
@CacheConfig(cacheManager = "userRedisCacheManager")
public class UserService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    @Qualifier("userRedisCacheManager")
    private RedisCacheManager userRedisCacheManager;

    /**
     * 由于 CacheManager 使用的是自定义的json序列化的redis模板 RedisTemplate<Object, Customer>
     * User对象可以使用json序列化存储到redis，但是从缓存查询到的json，不能反序列化为User对象。
     * 怎么办？
     * 根据不同的实体类，定义多个CacheManager，在Service类中，CacheConfig注解，指定使用哪个CacheManager
     */
    @Cacheable(value = {"user"})
    public User findById2(int uid) {
        System.out.println("查用户：uid:" + uid);
        return userMapper.findById(uid);
    }

    // 使用缓存管理器得到缓存进行api调用
    // 编码的方式来缓存
    public User findById(int uid) {
        System.out.println("查用户：uid:" + uid);
        User user = userMapper.findById(uid);
        // 获取某个缓存
        Cache cache = userRedisCacheManager.getCache("user");
        cache.put("user:1",user);
        return user;
    }
}
