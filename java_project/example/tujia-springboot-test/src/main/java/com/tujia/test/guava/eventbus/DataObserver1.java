package com.tujia.test.guava.eventbus;

import com.google.common.eventbus.Subscribe;

/**
 * @author xiaopengw
 * @date 2018/10/8
 * @remark 观察者一
 */
public class DataObserver1 {


    /**
     * 只有通过subscribe注解的方法才能注册到EventBus
     * 而且方法只能有一个参数
     * @param msg
     */
    @Subscribe
    public void func(String msg) {
        System.out.println("DataObserver1 String msg: " + msg);
    }




}
