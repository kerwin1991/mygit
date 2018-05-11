package com.wxp.config;

import com.wxp.service.TestServcie;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by wangxiaopeng on 2018/5/11.
 */

/**
 *
 */
@Configuration // 指名当前类是个配置类，替代spring配置文件： applicationContext-*.xml
public class MyAppConfig {

	// 将方法的返回值添加到容器中 默认的id就是方法名 替代   <bean>
	@Bean
	public TestServcie testServcie2() {
		return new TestServcie();
	}

}
