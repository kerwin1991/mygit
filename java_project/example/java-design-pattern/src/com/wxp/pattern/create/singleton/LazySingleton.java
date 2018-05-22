package com.wxp.pattern.create.singleton;

/**
 * 懒汉式单例
 */
public class LazySingleton {

    private static LazySingleton instance = null;


    private LazySingleton() {

    }

    /**
     * synchronized 处理多线程环境
     * 时间换空间 类装载时不创建对象，节省空间 ，需要的时候才创建
     * 优缺点：由于（优点）懒汉式的实现是线程安全的，这样会降低整个访问的速度，而且每次都要判
     * @return
     */
    public static synchronized LazySingleton getInstance(){

        if (instance == null) {
            instance = new LazySingleton();
        }
        return instance;
    }

}
