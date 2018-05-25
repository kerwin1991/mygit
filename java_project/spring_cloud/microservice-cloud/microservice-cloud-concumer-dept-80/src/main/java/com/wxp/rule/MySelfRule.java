package com.wxp.rule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import com.netflix.loadbalancer.RoundRobinRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 自定义 Ribbon 负载均衡 算法/规则
 */
@Configuration
public class MySelfRule {

	// 替换 默认 轮询 为 随机
	@Bean
	public IRule myRule() {
		//return new RandomRule(); // 随机
		//return new RoundRobinRule();  // 轮询
		return new MyRandomRule();  // 自定义的随机算法 每个轮询5次
	}

	/**
	 * 需求：轮询，但是 每个服务器要求被调用5次，默认每台服务器每次调一次轮到下一个服务器
	 */



}
