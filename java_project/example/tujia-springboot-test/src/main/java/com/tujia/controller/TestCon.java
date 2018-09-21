package com.tujia.controller;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by xiaopengw on 2018/7/10.
 */
public class TestCon {

    public static void main(String[] args) {
        AtomicBoolean atomicBoolean = new AtomicBoolean(true);
        // 表示 atomicBoolean 原先如果是false 就 修改为 true ，返回是否修改成功
        boolean b = atomicBoolean.compareAndSet(false, true);
        System.out.println(b);
        System.out.println(atomicBoolean);
    }

}
