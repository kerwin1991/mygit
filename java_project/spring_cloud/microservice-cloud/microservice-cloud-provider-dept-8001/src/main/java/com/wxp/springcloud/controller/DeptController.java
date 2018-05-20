package com.wxp.springcloud.controller;

import com.wxp.springcloud.entities.Dept;
import com.wxp.springcloud.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
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
}
