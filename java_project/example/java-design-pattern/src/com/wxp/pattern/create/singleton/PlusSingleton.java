package com.wxp.pattern.create.singleton;

/**
 * Lazy initialization holder class模式
 * 使用了Java的类级内部类和多线程缺省同步锁的知识，很巧妙地同时实现了延迟加载和线程安全。
 *
 * 类级内部类：
 * static修饰 成员内部类 【没static修饰 称为 对象级内部类】
 * 类级内部类相当于其外部类的成员，只有在第一次被使用的时候才被会装载
 * 其内部可以定义静态方法，方法中只能够引用外部类中的静态成员方法或者成员变量
 *
 * 多线程缺省同步锁：
 * 在多线程开发中，为了解决并发问题，主要是通过使用synchronized来加互斥锁进行同步控制。
 * 但是在某些情况中，JVM已经隐含地为您执行了同步，这些情况下就不用自己再来进行同步控制了。这些情况包括
 * - 由静态初始化器（在静态字段上或static{}块中的初始化器）初始化数据时
 * - 访问final字段时
 * - 在创建线程之前创建对象时
 * - 线程可以看见它将要处理的对象时
 *
 *
 */
public class PlusSingleton {

    private PlusSingleton() {

    }
    /**
     * 类级的内部类，也就是静态的成员式内部类，该内部类的实例与外部类的实例没有绑定关系，
     * 而且只有被调用到时才会装载，从而实现了延迟加载。
     */
    private static class SingletonHolder{
        private static PlusSingleton instance = new PlusSingleton();
    }

    public static PlusSingleton getInstance(){
        // 只有被调用到时才会装载，从而实现了延迟加载
        return SingletonHolder.instance;
    }

    /**
     * 优点：getInstance方法并没有被同步，并且只是执行一个域的访问，因此延迟初始化并没有增加任何访问成本。
     */
}
