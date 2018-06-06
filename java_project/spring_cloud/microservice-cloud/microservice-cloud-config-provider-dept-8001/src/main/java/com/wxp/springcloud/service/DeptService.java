package com.wxp.springcloud.service;

import com.wxp.springcloud.entities.Dept;

import java.util.List;

/**
 * Created by Administrator on 2018/5/20.
 */
public interface DeptService {


    boolean add(Dept dept);

    Dept get(Long id);

    List<Dept> list();



}
