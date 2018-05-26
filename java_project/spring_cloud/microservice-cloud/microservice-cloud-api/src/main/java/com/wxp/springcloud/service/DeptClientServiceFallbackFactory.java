package com.wxp.springcloud.service;

import com.wxp.springcloud.entities.Dept;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 实现 FallbackFactory 接口
 * 对 DeptClientService 这个接口里的服务做降级处理
 *
 *
 * 坑：千万不要忘记在这个类上加注解 @Component
 *
 */
@Component // 不要忘记添加这个注解
public class DeptClientServiceFallbackFactory implements FallbackFactory<DeptClientService>{
	@Override
	public DeptClientService create(Throwable throwable) {
		// 在此统一对每个方法做熔断处理
		return new DeptClientService() {
			@Override
			public boolean add(Dept dept) {
				return false;
			}

			@Override
			public Dept get(Long id) {
				return new Dept().setDeptno(id).setDname("该ID：[" + id + "], 没有对应的信息。Consumer客户端提供的降级信息，此刻服务Provider已经关闭").setDb_source("no this item in Mysql");
			}

			@Override
			public List<Dept> list() {
				return null;
			}
		};
	}
}
