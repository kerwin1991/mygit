package com.tujia.service;

import com.google.common.base.Optional;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.Maps;
import com.tujia.dto.People;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @author xiaopengw
 * @date 2018/8/28
 * @remark
 */
@Service
public class PeopleGCacheService {

    private LoadingCache<Integer, Optional<People>> peopleCache;

    public PeopleGCacheService() {
        this.peopleCache = buildChannelCache();
    }

    private Map<Integer, People> peopleMap = Maps.newHashMapWithExpectedSize(10);

    @PostConstruct
    public void init() {
        peopleMap.put(1, new People("张三", 1));
        peopleMap.put(2, new People("张四", 22));
        peopleMap.put(3, new People("张五", 33));
        peopleMap.put(4, new People("张六", 18));
        peopleMap.put(5, new People("张七", 28));
        peopleMap.put(6, new People("张八", 100));
    }

    private LoadingCache<Integer, Optional<People>> buildChannelCache() {
        LoadingCache<Integer, Optional<People>> cache = CacheBuilder.newBuilder()
                .maximumSize(500)
                .expireAfterAccess(12, TimeUnit.HOURS)
                .build(new CacheLoader<Integer, Optional<People>>() {
                    @Override
                    public Optional<People> load(Integer peopleId) throws Exception {
                        People basicInfo = getPeople(peopleId);
                        if (basicInfo == null) {
                            return Optional.absent();
                        }
                        return Optional.of(basicInfo);
                    }
                });
        return cache;
    }

    // 模拟查数据库
    private People getPeople(Integer peopleId) {
        People ret = null;
        return ret = peopleMap.get(peopleId) == null ? null : ret;
    }

    public People getFormGcache(Integer peopleId) {
        try {
            Optional<People> peopleOptional = peopleCache.get(peopleId);
            if (!peopleOptional.isPresent()) {
                return null;
            }
            return peopleOptional.get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }

}
