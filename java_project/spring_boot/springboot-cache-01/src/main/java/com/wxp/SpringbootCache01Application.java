package com.wxp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@MapperScan("com.wxp.mapper")
@SpringBootApplication
public class SpringbootCache01Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootCache01Application.class, args);
	}
}

/**
 *
 *快速体验缓存：
 * 	1开启基于注解缓存EnableCaching
 * 	2标注缓存注解即可
 *
 *
 *
 *
 *
 *
 *
 *
 * */