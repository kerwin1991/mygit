package com.wxp.amqp.bean;

/**
 * Created by Administrator on 2018/5/25.
 */
public class Product {

    private Long pid;
    private int account;
    private String desc;

    public Product() {
    }

    @Override
    public String toString() {
        return "Product{" +
                "pid=" + pid +
                ", account=" + account +
                ", desc='" + desc + '\'' +
                '}';
    }

    public Product(Long pid, int account, String desc) {
        this.pid = pid;
        this.account = account;
        this.desc = desc;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public int getAccount() {
        return account;
    }

    public void setAccount(int account) {
        this.account = account;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
