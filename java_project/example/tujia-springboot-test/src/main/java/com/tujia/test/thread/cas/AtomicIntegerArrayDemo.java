package com.tujia.test.thread.cas;

import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * @author xiaopengw
 * @date 2019/4/19
 * @remark
 */
public class AtomicIntegerArrayDemo {

    private static AtomicIntegerArray arr = new AtomicIntegerArray(10);

    public static class AddThread implements Runnable{
        @Override
        public void run() {
            for (int i = 0; i < 100000; i++) {
                arr.getAndIncrement(i % arr.length());
            }
        }
    }
    // 每个线程执行结束时 会给每个元素的值增加 +10000  ，开了12个线程  所以 加到 12 0000 。线程安全的，否则小于 120000
    public static void main(String[] args) throws InterruptedException {
        Thread[] ts = new Thread[12];
        for (int i = 0; i < 12; i++) {
            ts[i] = new Thread(new AddThread());
        }
        for (int i = 0; i < 12; i++) {
            ts[i].start();
        }
        for (int i = 0; i < 12; i++) {
            ts[i].join();
        }
        System.out.println(arr);
    }



}
