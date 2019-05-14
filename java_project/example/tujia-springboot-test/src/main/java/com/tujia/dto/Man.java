package com.tujia.dto;

/**
 * @author xiaopengw
 * @date 2019/4/26
 * @remark
 */
public class Man implements Cloneable{ // * 必须实现Clonable接口 否则抛异常 CloneNotSupportedException

    private int age;

    // *深度克隆 也要实现cloneable接口
    private Address address;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Man{" +
                "age=" + age +
                '}';
    }

    /**
     * 浅克隆
     * 1、
     * 2、覆盖clone()方法，访问修饰符设为public。方法中调用super.clone()方法得到需要的复制对象。（native为本地方法)
     * @return
     * @throws CloneNotSupportedException
     */
    @Override
    public Object clone() {
        Man man = null;
        try {
            man = (Man) super.clone(); // *必须类型强转  Object->Man
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        man.address = (Address) address.clone(); // *深度克隆
        return man;
    }
}
