package com.wxp.pojo;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/5/15.
 */
public class Customer implements Serializable{
    private int custId;
    private String custName;
    private String address;
    private String descInfo;

    @Override
    public String toString() {
        return "Customer{" +
                "custId=" + custId +
                ", custName='" + custName + '\'' +
                ", address='" + address + '\'' +
                ", descInfo='" + descInfo + '\'' +
                '}';
    }

    public String getDescInfo() {
        return descInfo;
    }

    public void setDescInfo(String descInfo) {
        this.descInfo = descInfo;
    }

    public int getCustId() {
        return custId;
    }

    public void setCustId(int custId) {
        this.custId = custId;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
