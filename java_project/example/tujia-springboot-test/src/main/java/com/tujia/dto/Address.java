package com.tujia.dto;

/**
 * @author xiaopengw
 * @date 2019/4/29
 * @remark
 */
public class Address implements Cloneable{

    private String add;

    public String getAdd() {
        return add;
    }

    public void setAdd(String add) {
        this.add = add;
    }

    @Override
    public Object clone() {
        Address cloneAddr = null;
        try {
            cloneAddr = (Address) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return cloneAddr;
    }
}
