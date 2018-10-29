package com.tujia.test.guava.eventbus;

/**
 * @author xiaopengw
 * @date 2018/10/8
 * @remark
 */
public class Test {

    public static void main(String[] args) {
        DataObserver1 dataObserver1 = new DataObserver1();
        DataObserver2 dataObserver2 = new DataObserver2();
        DataObserver3 dataObserver3 = new DataObserver3();

        EventBusCenter.register(dataObserver1);
        EventBusCenter.register(dataObserver2);
        EventBusCenter.register(dataObserver3);

        System.out.println("-------------------all");

        EventBusCenter.post("中国");
        EventBusCenter.post(123);

        System.out.println("-------------------unregister");

        EventBusCenter.unRegister(dataObserver2);


        EventBusCenter.post("中国");
        EventBusCenter.post(123);

    }

}
