package com.tujia.test.thread.cas;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author xiaopengw
 * @date 2019/3/29
 * @remark
 */
public class AtomicReferenceTest {

    public static final AtomicReference<String> atomicStr = new AtomicReference<>("abc");


    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(Math.abs((int) (Math.random()) * 100));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (atomicStr.compareAndSet("abc", "def")) {
                        System.out.println("Thread" + Thread.currentThread().getId() + "Change value to def");
                    } else {
                        System.out.println("Thread" + Thread.currentThread().getId() + "Fail");
                    }
                }
            }.start();
        }
    }


}
