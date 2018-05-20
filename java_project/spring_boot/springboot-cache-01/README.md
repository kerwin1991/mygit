## Springboot Cache ##

- 导入的自动配置类

0. CacheAutoConfiguration 

注解 @Import(CacheConfigurationImportSelector.class) 返回的 imports 包含所有的缓存配置类

```java
static class CacheConfigurationImportSelector implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        CacheType[] types = CacheType.values();
        String[] imports = new String[types.length];
        for (int i = 0; i < types.length; i++) {
            imports[i] = CacheConfigurations.getConfigurationClass(types[i]);
        }
        return imports;
    }

}
```

缓存的配置类，哪个默认生效呢？ debug 模式 得出默认生效类 SimpleCacheConfiguration

```
org.springframework.boot.autoconfigure.cache.GenericCacheConfiguration
org.springframework.boot.autoconfigure.cache.JCacheCacheConfiguration
org.springframework.boot.autoconfigure.cache.EhCacheCacheConfiguration
org.springframework.boot.autoconfigure.cache.HazelcastCacheConfiguration
org.springframework.boot.autoconfigure.cache.InfinispanCacheConfiguration
org.springframework.boot.autoconfigure.cache.CouchbaseCacheConfiguration
org.springframework.boot.autoconfigure.cache.RedisCacheConfiguration
org.springframework.boot.autoconfigure.cache.CaffeineCacheConfiguration
org.springframework.boot.autoconfigure.cache.GuavaCacheConfiguration
org.springframework.boot.autoconfigure.cache.SimpleCacheConfiguration
org.springframework.boot.autoconfigure.cache.NoOpCacheConfiguration
```

1. SimpleCacheConfiguration
    
    给容器中注入了了cacheManager，缓存管理器 类型 ConcurrentMapCacheManager
```
@Bean
public ConcurrentMapCacheManager cacheManager() {
    ConcurrentMapCacheManager cacheManager = new ConcurrentMapCacheManager();
    List<String> cacheNames = this.cacheProperties.getCacheNames();
    if (!cacheNames.isEmpty()) {
        cacheManager.setCacheNames(cacheNames);
    }
    return this.customizerInvoker.customize(cacheManager);
}
```
2. ConcurrentMapCacheManager 实现 CacheManager

API :
Cache getCache(String name) 
    根据名字可以得到缓存组件。所有的Cache组件存在 ConcurrentMap<String, Cache> cacheMap
    获取或者创建一个，ConcurrentMapCache 类型的Cache组件。
    
3. ConcurrentMapCache
    
    作用：将数据保存在 ConcurrentMap<Object, Object> store

    有一些缓存的方法： lookup get put 属性 store
    
ConcurrentMap<Object, Object> store    

protected Object lookup(Object key)

运行步骤：
@Cacheable(cacheNames = {"cust"})  该注解加在service类的某查询方法上
- 1、service方法运行之前 CacheManager 根据 cacheName "cust" 获取 Cache组件 第一次获取缓存会创建出来
    public Cache getCache(String name)
- 2、执行 Cache 组件 lookup方法，查找是否已缓存， 入参key，就是 Service 方法生成的
    protected Object lookup(Object key) 
    key 是按照某种策略生成的。默认是使用 KeyGenerator 实现类 SimpleKeyGenerator 生成的
        策略：如果没有参数，key = new SimpleKey()
             如果是1个参数，key = 参数的值
             如果是多个参数， key = new SimpleKey(params)
    如果没有缓存该service方法的返回值结果，就执行service方法，成功返回结果后，将结果放到缓存
    执行 Cache 组件的 put 方法
    public void put(Object key, Object value) {
        this.store.put(key, toStoreValue(value));
    }
总结：@Cacheable标注的方法执行之前，先检查缓存有没有这个数据，默认按照参数的作为key去查询缓存，如果没有，就运行方法，并将结果放到缓存。
    以后再来调用，就可以从缓存获取了。
核心：
  1 使用CacheManager【ConcurrentMapCacheManager】按照名字得到Cache【ConcurrentMapCache】组件；
  2 key 使用KeyGenerator默认是【SimpleKeyGenerator】生成的
  
- @Cacheable 注解其它属性用法

@Cacheable(cacheNames = {"cust"})  等同与 @Cacheable(value = {"cust"}) 
    指定缓存组件名称，且是数组类型，将方法的返回值放到哪个缓存组件Cache


开发中，使用缓存中间件，如 redis memchached

## 整合Redis ##

0. 引入启动 spring-boot-starter-data-redis
1. 配置ip spring.redis.host=127.0.0.1
2. 测试缓存 改变redis默认jdk序列化器为json序列化
3. 原理：CacheManager === Cache 


[redis官网](http://www.redis.cn/)

[docker官方镜像](https://hub.docker.com/) 反应很慢

[docker中国](http://www.docker-cn.com/) 镜像加速

命令
sudo docker pull registry.docker-cn.com/library/redis
docker images 查看已下载的镜像
docker run -d -p 6379:6379 --name myredis     // -d 后台的方式启动 -p 暴露端口    