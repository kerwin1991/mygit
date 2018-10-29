package com.tujia.test.guava.eventbus;

import com.google.common.eventbus.EventBus;

/**
 * @author xiaopengw
 * @date 2018/10/8
 * @remark
 */
public class EventBusCenter {

    private static EventBus eventBus = new EventBus();

    public static EventBus getInstance() {
        return eventBus;
    }

    public static void register(Object obj) {
        eventBus.register(obj);
    }
    public static void unRegister(Object obj) {
        eventBus.unregister(obj);
    }
    public static void post(Object obj) {
        eventBus.post(obj);
    }
}
