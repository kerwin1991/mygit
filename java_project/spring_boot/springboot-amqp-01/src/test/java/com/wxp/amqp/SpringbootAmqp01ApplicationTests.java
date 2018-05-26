package com.wxp.amqp;

import com.wxp.amqp.bean.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootAmqp01ApplicationTests {

	@Autowired
	RabbitTemplate rabbitTemplate;

	@Autowired
	AmqpAdmin amqpAdmin;

	@Test
	public void direc() {
		// 构造消息  ：  消息头 消息体
		// rabbitTemplate.send("","",Message);
		// 可以转换，只需要传入要发送的对象，自动序列化，保存发送给rabbitmq。默认当成消息体
		// 对象默认java序列化后发送出去
		Map<String, Object> body = new HashMap<>();
		body.put("name", "wangxiaopeng2");
		body.put("company", "enci");
		// convertAndSend 方法，可以直接将对象当做消息体发送出去。 使用 Pojo 也是可以的
		rabbitTemplate.convertAndSend("encicar.direct","frmana",body);
	}

	// MessageConverter 默认 SimpleMessageConverter 改变它，实现json序列化
	@Test
	public void receive() {
		// receiveAndConvert 可以将消息接受并且按照原数据类型转换  这里是HashMap类型 可以强转 再取出来里面的值o
		Object message = rabbitTemplate.receiveAndConvert("frmana");
		System.out.println(message.getClass());
		System.out.println(message);
		Map<String, Object> receive = (Map<String, Object>) message;
		System.out.println(receive.get("name"));
		System.out.println(receive.get("company"));
	}

	// 广播
	@Test
	public void fanout() {
		rabbitTemplate.convertAndSend("encicar.fanout","",new Product(1L,2,"苹果"));
	}
	@Test
	public void receiveFanout() {
		Object message = rabbitTemplate.receiveAndConvert("encicar.news");
		System.out.println(message.getClass());
		// 注意：json反序列化，自己创建的pojo需要提供空参构，否则序列化失败
		Product product = (Product) message;
		System.out.println(product.getAccount());
		System.out.println(product.getDesc());
	}

	// topic
	@Test
	public void topic() {
		rabbitTemplate.convertAndSend("encicar.topic","frmana.wxp",new Product(2L,3,"香蕉"));
	}

	// 代码创建交换机
	@Test
	public void createExchage() {
		// 创建交换机
//		amqpAdmin.declareExchange(new DirectExchange("code-direct"));
		// 创建队列
//		amqpAdmin.declareQueue(new Queue("code-queue", true));
		// 交换机与队列绑定 参数：目的地（队列名称），目的类型(队列)，交换机名称，路由键，参数
		amqpAdmin.declareBinding(new Binding("code-queue", Binding.DestinationType.QUEUE,"code-direct","haha2018",null));
	}

}
