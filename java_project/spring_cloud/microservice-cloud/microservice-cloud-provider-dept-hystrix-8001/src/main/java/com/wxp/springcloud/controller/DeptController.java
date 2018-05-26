package com.wxp.springcloud.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.wxp.springcloud.entities.Dept;
import com.wxp.springcloud.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 服务熔断演示 *
 */
@RestController
@RequestMapping(value = "/dept")
public class DeptController {

	@Autowired
	private DeptService deptService;

	// 服务发现
	@Autowired
	private DiscoveryClient client;

	/**
	 * 定义方法 符合 RESTful
	 *
	 * @param dept
	 * @return
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public boolean add(@RequestBody Dept dept) {
		return deptService.add(dept);
	}

	/**
	 * @HystrixCommand
	 * 缺点：
	 * 在Controller上处理；
	 * 方法膨胀，每个业务方法都要对应一个异常处理的 fallbackMethod  业务方法数量*2
	 * 处理异常和业务逻辑绑定在一块，高耦合，程序的维护性、健壮性、美观性，不OK。
	 *
	 * 新的方法：实现解耦，和分离。避免方法膨胀。在service接口上处理熔断。
	 *
	 */
	@RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
	// 熔断异常处理
	// 一旦调用服务方法失败并抛出了错误信息后，会自动调用@HystrixCommand标注好的 fallbackMethod 调用类中指定方法
	@HystrixCommand(fallbackMethod = "processHystrix_Get")
	public Dept get(@PathVariable("id") Long id) {
		Dept dept = deptService.get(id);
		// 模拟 微服务不可以，如，响应时间过长，不可用等。   测试服务熔断 抛出运行时异常 。 就是个 异常处理。
		if (null == dept) {
			throw new RuntimeException("该ID：[" + id + "], 没有对应的信息。");
		}
		return dept;
	}

	public Dept processHystrix_Get(@PathVariable("id") Long id) {
		return new Dept().setDeptno(id).setDname("该ID：[" + id + "], 没有对应的信息。null--@HystrixCommand").setDb_source("no this item in Mysql");
	}

	@RequestMapping(value = "/get/list", method = RequestMethod.GET)
	public List<Dept> list() {
		return deptService.list();
	}


	/**
	 * 服务发现
	 * Autowired
	 * private DiscoveryClient client;
	 * 配合启动类注解： @EnableDiscoveryClient
	 *
	 * @return
	 */
	@RequestMapping(value = "/discovery", method = RequestMethod.GET)
	public Object discovery() {
		// 从eureka server 中获取所有微服务 部门 订单 。。
		List<String> list = client.getServices();
		System.out.println("***发现服务***：" + list);
		// 获取某个微服务 根据名称 ：比如 部门  。 暴露服务
		List<ServiceInstance> instances = client.getInstances("MICROSERVICE-CLOUD-DEPT");
		for (ServiceInstance item : instances) {
			System.out.println(
					item.getServiceId() + "\t" +
							item.getHost() + "\t" +
							item.getPort() + "\t" +
							item.getUri());
		}
		return this.client;
	}
}
