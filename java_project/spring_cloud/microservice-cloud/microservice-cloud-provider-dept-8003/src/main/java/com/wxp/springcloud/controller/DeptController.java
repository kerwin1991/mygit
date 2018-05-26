package com.wxp.springcloud.controller;

import com.wxp.springcloud.entities.Dept;
import com.wxp.springcloud.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 前后端分离 rest风格 转为json
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
     * @param dept
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public boolean add(@RequestBody Dept dept) {
        return deptService.add(dept);
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public Dept get(@PathVariable("id") Long id) {
        return deptService.get(id);
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
     * @return
     */
    @RequestMapping(value = "/discovery", method = RequestMethod.GET)
    public Object discovery() {
        // 从eureka server 中获取所有微服务 部门 订单 。。
        List<String> list = client.getServices();
        System.out.println("***发现服务***："+list);
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
