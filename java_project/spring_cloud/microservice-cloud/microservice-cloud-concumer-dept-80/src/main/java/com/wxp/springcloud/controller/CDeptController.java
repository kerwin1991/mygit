package com.wxp.springcloud.controller;

import com.wxp.springcloud.entities.Dept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * 消费方的controller  不应该有service的
 */
@RestController
@RequestMapping("/consumer")
public class CDeptController {

    /**
     * restTemplate 可以完成rest接口的调用 类似浏览器？
     * 提供了多种便捷访问远程HTTP服务的方法，类似 http-client
     * 是一种简单便捷的访问restful服务模板类，是Spring提供的用于访问Rest服务的客户端模板工具集     *
     */
    @Autowired
    private RestTemplate restTemplate;

    private static final String REST_URL_PREFIX = "http://localhost:8001";

    /**
     * Rest请求地址，请求参数，HTTP响应被转换成的对象类型
     * String url, Object request, Class<T> responseType
     * @param dept
     * @return
     */
    @RequestMapping(value = "/dept/add")
    public boolean add(Dept dept) {
        return restTemplate.postForObject(REST_URL_PREFIX + "/dept/add", dept, Boolean.class);
    }

    @RequestMapping(value = "/dept/get/{id}")
    public Dept get(@PathVariable("id") Long id) {
        return restTemplate.getForObject(REST_URL_PREFIX + "/dept/get/"+id, Dept.class);
    }
    @RequestMapping(value = "/dept/list")
    public List<Dept> get() {
        return restTemplate.getForObject(REST_URL_PREFIX + "/dept/get/list", List.class);
    }
}
