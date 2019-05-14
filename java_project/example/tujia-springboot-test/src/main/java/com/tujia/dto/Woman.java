package com.tujia.dto;

import java.io.*;

/**
 * @author xiaopengw
 * @date 2019/5/10
 * @remark
 */
public class Woman implements Serializable { // 深度克隆 需要实现序列化接口

    private static final long serialVersionUID = 2305437269881375615L;

    // 出生年
    private int birthYear;

    private Location location; //  所有引用类型成员也要实现序列化接口

    /**
     * 流 实现深度克隆
     * @return
     */
    public Woman  cloneWoman() {
        Woman woman = null;
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(this);

            ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(bais);
            woman = (Woman) ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return woman;
    }


    public int getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(int birthYear) {
        this.birthYear = birthYear;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "Woman{" +
                "birthYear=" + birthYear +
                ", location=" + location +
                '}';
    }
}
