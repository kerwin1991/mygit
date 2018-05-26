package com.wxp.amqp.service;

import com.wxp.amqp.bean.Product;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

/**
 * 监听消息 接受 Rabbitmq 消息
 */
@Service
public class ProductService {

    @RabbitListener(queues = {"frmana.cust"})
    public void productReceive(Product product) {
        System.out.println("收到消息");
        System.out.println(product);
    }

    @RabbitListener(queues = {"frmana"})
    public void receive2(Message message) {
        System.out.println("消息体："+ message.getBody());
        System.out.println("消息头："+message.getMessageProperties());
    }
}
