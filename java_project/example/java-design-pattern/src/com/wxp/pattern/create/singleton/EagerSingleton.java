package com.wxp.pattern.create.singleton;

/**
 * 饿汉单例
 */
public class EagerSingleton {
    // 装载类的时候就创建对象实例 非延迟加载 无线程安全问题
    private static EagerSingleton instance = new EagerSingleton();
    /**
     * 私有默认构造子
     */
    private EagerSingleton(){}
    /**
     * 静态工厂方法
     */
    public static EagerSingleton getInstance(){
        return instance;
    }

}
