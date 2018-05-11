package com.wxp;

import com.wxp.pojo.Person;
import com.wxp.pojo.PersonOne;
import com.wxp.pojo.Project;
import com.wxp.pojo.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * springboot 单元测试
 * RunWith springboot 的驱动器
 * 可在测试期间方便类似编码一样自动注入
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootSggOneApplicationTests {

	// 配置类
	@Autowired
	private Person person;
	@Autowired
	private Student student;
	@Autowired
	private PersonOne personOne;
	@Autowired
	private Project project;


	// 注入spring容器
	@Autowired
	ApplicationContext ioc;

	/**
	 * 属性注入测试
	 */
	@Test
	public void contextLoads() {
//		System.out.println(person);
		System.out.println(student);
	}

	/**
	 * 配置类替换配置文件测试
	 */
	@Test
	public void contextPs() {
		System.out.println(personOne);

		// 判断容器中是否包含某个bean   如在配置文件中 <bean id='testServcie' class='xxxxxxxxxx'></bean>
		boolean b = ioc.containsBean("testServcie");
		System.out.println(b);
	}

	/**
	 * 配置文件占位符测试
	 */
	@Test
	public void zhanWeiFu() {
		System.out.println(project);
		// description='random_num_aa3a0ef9-7a0e-413b-aeaa-6e88f5f956f8_springboot', val1='random_num_6eced762-4290-4b3d-80b5-0dcbd683632b', val2='-2029926916', val3='defaultVal_hi_boot'
	}

}