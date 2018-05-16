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
	 * 根据当前不同的条件判断，决定这个配置类是否生效
	 *
	 * 精髓：springboot启动加载大量自动配置类；我们看我们需要的功能有没有springboot默认写好的自动配置类；再看，这个自动配置类中到底配了哪些组件，只要我们要用的组件有，我们就不需要再来配置了；
	 * 给容器中自动配置类添加组件的时候，会从properties类中获取某些属性值，我们就可以在配置文件中指定这些属性值；
	 * xxxAutoConfiguration : 自动配置类；给容器中添加配置组件
	 * xxxProperties封装配置文件的内容
	 *
	 *
	 * @Conditional派生注解  @Conditional*
	 * 必须是该注解指定的条件成立，才给容器中添加
	 * 自动配置类，必须在一定的条件下才能生效，java版本，导入了什么依赖，等
	 *
	 * debut=true  开启springboot的debug模式
	 * Positive matches ：自动配置类启用的
	 * Negative matches ：没有启用的
	 *
	 * */

}


