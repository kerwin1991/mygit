package com.wxp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

/**
 * Created by wangxiaopeng on 2018/5/16.
 */
@Controller
public class PageController {

	@RequestMapping("/success")
	public String success(Map<String, Object> map) {
		// classpath:/templates/*.html  thymeleaf 帮忙解析
		map.put("companyName", "新华世纪电子商务有限公司");
		return "success";
	}

}
