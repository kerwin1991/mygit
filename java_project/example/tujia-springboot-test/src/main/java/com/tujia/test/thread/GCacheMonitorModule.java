package com.tujia.test.thread;

import com.google.common.cache.Cache;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author xiaopengw
 * @date 2018/8/24
 * @remark guava cache监控服务类 原型
 */
public class GCacheMonitorModule {
    private final static Map<String, Cache> CACHE_REPOSITORY = new ConcurrentHashMap<String, Cache>();
    private final static Map<String, String> STRING_REPOSITORY = new ConcurrentHashMap<String, String>();
    private final static ScheduledExecutorService SCHEDULED = Executors.newSingleThreadScheduledExecutor(
            new ThreadFactoryBuilder().setNameFormat("gcache_monitor_thread_%d").build());
    private final static Logger LOGGER = LoggerFactory.getLogger("gcacheMonitor");
    private final static String TAB_DELIM = "\t";

    private final static Runnable statsRecorder = new Runnable() {

        /*@SuppressWarnings("rawtypes")
        @Override
        public void run() {
            try {
                if (CACHE_REPOSITORY.isEmpty()) {
                    return;
                }
                for (Entry<String, Cache> entry : CACHE_REPOSITORY.entrySet()) {
                    record(entry);
                }
            } catch (Throwable e) {
                LOGGER.error("statsReport.run error", e);
            }
        }

        private void record(Entry<String, Cache> entry) {
            StringBuilder stringBuilder = new StringBuilder();
            Cache cache = entry.getValue();
            CacheStats cacheStats = cache.stats();
            stringBuilder.append(entry.getKey()).append(TAB_DELIM).append(cache.size()).append(TAB_DELIM)
                    .append(format(cacheStats.hitRate())).append(TAB_DELIM).append(cacheStats.hitCount())
                    .append(TAB_DELIM).append(cacheStats.missCount()).append(TAB_DELIM)
                    .append(format(cacheStats.loadExceptionRate())).append(TAB_DELIM)
                    .append(cacheStats.loadExceptionCount()).append(TAB_DELIM).append(cacheStats.loadSuccessCount())
                    .append(TAB_DELIM).append(cacheStats.evictionCount()).append(TAB_DELIM)
                    .append(format((cacheStats.averageLoadPenalty() / 1000000)));
            LOGGER.info(stringBuilder.toString());
        }

        private double format(double d) {
            BigDecimal bg = new BigDecimal(d);
            return bg.setScale(4, BigDecimal.ROUND_DOWN).doubleValue();
        }*/

        @SuppressWarnings("rawtypes")
        @Override
        public void run() {
            try {
                if (STRING_REPOSITORY.isEmpty()) {
                    return;
                }
                for (Map.Entry<String, String> entry : STRING_REPOSITORY.entrySet()) {
                    LOGGER.info("监控啊,key:{},value:{}", entry.getKey(), entry.getValue());
                }
            } catch (Throwable e) {
                LOGGER.error("statsReport.run error", e);
            }
        }
    };

    public final static GCacheMonitorModule INSTANCE = new GCacheMonitorModule();

    public void registerCache(String cacheName, Cache cache) {
        CACHE_REPOSITORY.put(cacheName, cache);
    }

    // 测试
    public void registerString(String cacheName, String cache) {
        STRING_REPOSITORY.put(cacheName, cache);
    }

    private GCacheMonitorModule() {
        try {
            //  第一次执行时5s后，之后每次间隔20s执行一次!!!!!!!!
            SCHEDULED.scheduleAtFixedRate(statsRecorder, 5, 20, TimeUnit.SECONDS);
        } catch (Throwable e) {
            LOGGER.error("GCacheMonitor construct error", e);
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            GCacheMonitorModule.INSTANCE.registerString(String.valueOf(i), "cache" + (i+1));
        }
    }
}
