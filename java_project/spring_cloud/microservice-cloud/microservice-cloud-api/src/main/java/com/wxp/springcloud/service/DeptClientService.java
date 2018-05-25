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
 */
@FeignClient(value = "MICROSERVICE-CLOUD-DEPT")
public interface DeptClientService {

	@RequestMapping(value = "/dept/add", method = RequestMethod.POST)
	boolean add(@RequestBody Dept dept);

	@RequestMapping(value = "/dept/get/{id}", method = RequestMethod.GET)
	Dept get(@PathVariable("id") Long id);

	@RequestMapping(value = "/dept/get/list", method = RequestMethod.GET)
	List<Dept> list();

}
