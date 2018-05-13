package com.wxp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

//@ImportResource(locations = {"classpath:applicationContext.xml"})
@SpringBootApplication
public class SpringBootSggOneApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootSggOneApplication.class, args);
	}


	/**
	 *
	 * 默认不支持jsp，可以使用模板引擎 framarker thymeleaf
	 * 配置文件默认全局配置文件，名字是固定的。application.yml  application.properties
	 * 自动配置的默认值，springboot底层自动配置默认值
	 * yaml 不是一个标记语言 以数据我中心
	 *
	 *
	 * 支持加载自定义的applicationContext-*.xml 使用注解 @ImportResource
	 * @ImportResource(locations = {"classpath:applicationContext.xml"}) 导入spring的配置文件，让它生效。
	 * 以上用法不推荐；然而springboot推荐的的方式：
	 * 配置类 替换 配置文件  推荐全注解开发  @Configuration
	 *
	 *
	 * 约定优于配置的理念
	 *
	 *
	 *
	 * */
}


