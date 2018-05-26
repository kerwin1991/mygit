package com.wxp.springcloud.service;

import com.wxp.springcloud.entities.Dept;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 *  @FeignClient  接口 + 注解 的方式调用微服务 。面向接口编程。。。  同 @Mapper  dao接口，调用数据库
 *
 *  fallbackFactory = DeptClientServiceFallbackFactory.class
 *  表示这个接口中的服务调用出现问题，去找 DeptClientServiceFallbackFactory 统一分别处理。实现解耦。
 *
 */
// @FeignClient(value = "MICROSERVICE-CLOUD-DEPT")
@FeignClient(value = "MICROSERVICE-CLOUD-DEPT", fallbackFactory = DeptClientServiceFallbackFactory.class)  // 增加服务熔断处理
public interface DeptClientService {

	@RequestMapping(value = "/dept/add", method = RequestMethod.POST)
	boolean add(@RequestBody Dept dept);

	@RequestMapping(value = "/dept/get/{id}", method = RequestMethod.GET)
	Dept get(@PathVariable("id") Long id);

	@RequestMapping(value = "/dept/get/list", method = RequestMethod.GET)
	List<Dept> list();

}
