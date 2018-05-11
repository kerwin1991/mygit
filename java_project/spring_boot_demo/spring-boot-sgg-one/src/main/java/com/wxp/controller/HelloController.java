package com.wxp.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by wangxiaopeng on 2018/5/9.
 */
@RestController
@RequestMapping("/demo")
public class HelloController {


	@RequestMapping("/hello")
	public String hello() {
		return "hello world";
	}

}
