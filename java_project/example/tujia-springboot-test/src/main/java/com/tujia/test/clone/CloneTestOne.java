package com.tujia.test.clone;

import com.tujia.dto.Address;
import com.tujia.dto.Man;

/**
 * @author xiaopengw
 * @date 2019/4/26
 * @remark
 */
public class CloneTestOne {

    public static void main(String[] args) {

        new CloneTestOne().fn3();

    }

    // 证明是浅克隆 对象里的对象没有拥有独立内存空间  -- 已改为深度克隆
    private void fn3() {
        Man man = new Man();
        Address address = new Address();
        address.setAdd("平遥县");
        man.setAge(100);
        man.setAddress(address);
        Man cloneMan = (Man) man.clone();
        System.out.println("man:add="+ man.getAddress().getAdd());
        System.out.println("cloneMan:add="+ cloneMan.getAddress().getAdd());
        System.out.println("-----------修改---------------");
        man.setAge(200);
        address.setAdd("文水县");
        System.out.println("man:add="+ man.getAddress().getAdd() +",age="+man.getAge());
        System.out.println("cloneMan:add="+ cloneMan.getAddress().getAdd()+",age="+cloneMan.getAge());
    }

    // 浅克隆
    public void fn2() {
        Man man = new Man();
        man.setAge(100);
        Man man2 = (Man) man.clone();
        System.out.println("man:age="+man.getAge());
        System.out.println("man2:age="+man2.getAge());
        System.out.println(man.equals(man2));
        man2.setAge(200);
        System.out.println("man:age="+man.getAge());
        System.out.println("man2:age="+man2.getAge());
        System.out.println(man == man2);
        System.out.println(man.equals(man2));

    }

    public void fn1() {
        Man man = new Man();
        man.setAge(100);
        Man man2 = man;
        man2.setAge(200);
        System.out.println(man);
        System.out.println(man2);
    }

    /**
     * 两者区别在于：是否引用数据类型中成员变量的复制（克隆）
     *
     * 浅克隆
     * 在浅克隆中，如果原型对象的成员变量是值类型，将复制一份给克隆对象；如果原型对象的成员变量是引用类型，则将引用对象的地址复制一份给克隆对象，也就是说原型对象和克隆对象的成员变量指向相同的内存地址。
     *
     *
     *
     *
     * 深克隆
     *
     * 在深克隆中，无论原型对象的成员变量是值类型还是引用类型，都将复制一份给克隆对象，深克隆将原型对象的所有引用对象也复制一份给克隆对象。
     * 在Java语言中，如果需要实现深克隆，可以通过覆盖Object类的clone()方法实现，也可以通过序列化(Serialization)等方式来实现。
     *
     * 序列化就是将对象写到流的过程，写到流中的对象是原有对象的一个拷贝，而原对象仍然存在于内存中。通过序列化实现的拷贝不仅可以复制对象本身，
     * 而且可以复制其引用的成员对象，因此通过序列化将对象写到一个流中，再从流里将其读出来，可以实现深克隆。需要注意的是能够实现序列化的对象其类必须实现Serializable接口，否则无法实现序列化操作。
     *
     * 标识接口：
     * Java语言提供的Cloneable接口和Serializable接口的代码非常简单，它们都是空接口，这种空接口也称为标识接口，标识接口中没有任何方法的定义，
     * 其作用是告诉JRE这些接口的实现类是否具有某个功能，如是否支持克隆、是否支持序列化等。
     */
}
