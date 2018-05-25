package com.wxp.springcloud.controller;

import com.wxp.springcloud.entities.Dept;
import com.wxp.springcloud.service.DeptClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 消费方的controller  不应该有service的
 */
@RestController
@RequestMapping(value = "/consumer")
public class CDeptController {

    @Autowired
    private DeptClientService deptClientService;

    // todo： but 这个添加功能失败
    @RequestMapping(value = "/dept/add", method = RequestMethod.GET)
    boolean add(@RequestBody Dept dept){
        System.out.println(dept);
        return deptClientService.add(dept);
    }

    @RequestMapping(value = "/dept/get/{id}", method = RequestMethod.GET)
    Dept get(@PathVariable("id") Long id){
        return deptClientService.get(id);
    }

    @RequestMapping(value = "/dept/get/list", method = RequestMethod.GET)
    List<Dept> list(){
        return deptClientService.list();
    }
}
