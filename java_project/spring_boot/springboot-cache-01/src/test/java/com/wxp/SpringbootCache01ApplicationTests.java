package com.wxp;

import com.wxp.mapper.CustomerMapper;
import com.wxp.pojo.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootCache01ApplicationTests {

	@Autowired
	private CustomerMapper customerMapper;

	@Autowired
	RedisTemplate redisTemplate; // kv 都是对象

	@Autowired
	StringRedisTemplate stringRedisTemplate; // 操作字符串 kv 都是字符串

	@Autowired // 自定义了redis的序列化规则
	RedisTemplate<Object, Customer> custRedisTemplate;


	@Test
	public void contextLoads() {

		Customer customer = customerMapper.selectById(1);
		System.out.println(customer);
	}

	/**
	 * string 字符串  list 列表  set 集合  zset 有序集合  hash 散列
	 * 常见五大数据类型 template 都可以操作
	 */
	@Test
	public void redis_test01() {
//		stringRedisTemplate.opsForValue().append("msg", "hello");
//		stringRedisTemplate.opsForValue().append("msg", " world");
//		stringRedisTemplate.opsForList().leftPush("mylist", "abc");
//		stringRedisTemplate.opsForList().leftPush("mylist", "ABC");

		/**
		 * 默认如果保存对象，使用jdk序列化机制，序列化后的数据保存到redis中
		 * 希望以json的方式存储
		 * 	1、自己转
		 * 	2、redisTemplate有默认的序列化规则，改变默认的序列化规则 MyRedisConfig
		 */
//		Customer customer = customerMapper.selectById(1);
//		redisTemplate.opsForValue().set("cust:"+customer.getCustId(),customer);
		redisTemplate.delete("cust:1");
	}

	@Test
	public void redis_test02() {
		Customer customer = customerMapper.selectById(1);
		custRedisTemplate.opsForValue().set("cust-01", customer);
	}



	public static void main(String[] args) throws UnsupportedEncodingException {
		String name = "王 晓 鹏";
		byte[] bytes = name.getBytes("UTF-8");
		byte[] bytes2 = name.getBytes("UTF-16");
		System.out.println(Arrays.toString(bytes));
		System.out.println(Arrays.toString(bytes2));

	}

}
