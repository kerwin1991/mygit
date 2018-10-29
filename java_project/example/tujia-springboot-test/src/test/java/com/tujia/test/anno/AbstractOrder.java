package com.tujia.test.anno;

import com.tujia.annotation.OrderAlias;

import javax.annotation.PostConstruct;

/**
 * @author xiaopengw
 * @date 2018/10/9
 * @remark
 */
public abstract class AbstractOrder {

    /**
     * 启动时注册 和 spring加载完成后自动注册原理一致 可 二选一
     */
    @PostConstruct
    public void register() {
        OrderHandlerFactory.registerOrderService(getAlias(),this);
    }


    public OrderAlias getAlias() {
        return this.getClass().getAnnotation(OrderAlias.class);
    }

}
