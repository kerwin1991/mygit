package com.wxp.service;

import com.wxp.mapper.CustomerMapper;
import com.wxp.pojo.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.*;
import org.springframework.stereotype.Service;

/**
 *@CacheConfig 可以定义类中公共注解属性 方法上的注解 value 属性可以省略
 */
@CacheConfig(cacheNames = {"cust"}, cacheManager = "customerRedisCacheManager")
@Service
public class CustomerService {
    @Autowired
    private CustomerMapper mapper;

    /**
     * 运行时机：先查缓存，如果缓存中没有值，就执行方法，再把方法的执行结果缓存
     * 将方法的返回值缓存，以后有相同的请求，直接从缓存获取值，不查数据库了
     * @param id
     * @return
     */
//    @Cacheable(value = {"cust"},key = "#root.methodName + '[' + #id + ']'")
//    @Cacheable(value = {"cust"}, keyGenerator = "myKeyGenerator")
   /* @Cacheable(value = {"cust"},
            condition = "#a0 > 1",
            unless = "#a0 == 2") // id 大于1才缓存  =2不缓存*/
    @Cacheable(value = {"cust"})
    public Customer selectById(int id) {
        System.out.println("查询id:" + id);
        return mapper.selectById(id);
    }

    /**
     * key SPEL表达式自定义缓存key的命名
     * #root.methodName  获取方法名
     * #id 属性名称为id的入参  @a0 第一个入参
     * 自定义keygenerator 来生成
     * <p>
     * condition 指符合条件的情况，才缓存
     */

    /**
     * @CachePut 运行时机：先执行方法，再将方法的返回结果缓存
     * 但是，缓存使用的key默认采用此更新方法的入参，为了能够达到更新查询时的缓存，
     * 在此，需要指定key生成方式，同查询时使用的key。
     * key = "#customer.custId"
     * key = "#result.custId"  使用方法返回值的属性作为缓存的key
     *
     * 如此：就可以既调用方法，又更新缓存数据。同步更新缓存。【缓存使用的key要一样，达到更新效果】
     *
     *
     * 注意：
     *      Cacheable 注解，不能使用#result，因为它的其中一个运行时机是方法执行前，还拿不到结果的返回结果
     */
    @CachePut(value = {"cust"}, key = "#customer.custId")
    public Customer update(Customer customer) {
        mapper.updateById(customer);
        System.out.println("数据库更新成功："+customer.getCustId());
        return customer;
    }


    /**
     * @CacheEvict 缓存清楚
     * 默认是在方法执行之后，删除缓存。
     * allEntries = true 删除所有缓存
     *
     * 默认方法执行后清楚缓存，如果方法异常，缓存不删除。
     * beforeInvocation = true 在方法执行前删除缓存，即便方法会报错，也清楚
     */
    @CacheEvict(value = {"cust"}, key = "#custId"/*, allEntries = true*/
        ,beforeInvocation = true
    )
    public void deleteById(int custId) {
        System.out.println("删除客户：custId：" + custId);
        // mapper.deleteCust(custId);
        int a = 10/0;
    }

    /**
     * 组合注解：@Caching 定义复杂的缓存规则
     *
     * 把方法的返回结果，根据custName缓存，根据custId address 以更新的方式缓存
     * 那么再用id查询是，就直接走缓存了。
     *
     * 但是，由于 CachePut 使用，方法要先执行，所以，数据库查询必须走一次。
     *
     */
    @Caching(cacheable = {
            @Cacheable(/*value = "cust",*/ key = "#custName")
            },
            put = {
            @CachePut(/*value = "cust",*/ key = "#result.custId"),
            @CachePut(value = "cust", key = "#result.address"),
            }
    )
    public Customer queryByName(String custName) {
        System.out.println("根据custName查：" + custName);
        Customer customer = mapper.queryByName(custName);
        return customer;
    }

}
