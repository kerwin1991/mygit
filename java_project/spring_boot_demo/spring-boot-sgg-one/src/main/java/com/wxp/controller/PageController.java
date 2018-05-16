package com.wxp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by wangxiaopeng on 2018/5/16.
 */
@Controller
public class PageController {

	@RequestMapping("/success")
	public String success() {
		// classpath:/templates/*.html  thymeleaf 帮忙解析
		return "success";
	}

}
