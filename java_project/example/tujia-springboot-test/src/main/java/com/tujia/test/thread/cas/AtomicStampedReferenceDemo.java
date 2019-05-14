package com.tujia.test.thread.cas;

import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @author xiaopengw
 * @date 2019/3/29
 * @remark
 */
public class AtomicStampedReferenceDemo {


    public static final AtomicStampedReference<Integer> money = new AtomicStampedReference<>(19, 0);
    // 模拟给用户充值，只能充值一次
    public static void main(String[] args) {
        // 模拟多个线程同时更新后台数据库  为用户充值
        for (int i = 0; i < 3; i++) {
            // 记录一开始时间戳  使用这个时间戳充值，多次充值，只能一次成功充值
            final int timestamp = money.getStamp();
            new Thread() {
                @Override
                public void run() {

                    while (true) {
                        while (true) {
                            Integer m = money.getReference();
                            if (m < 20) {
                                if (money.compareAndSet(m, m + 20, timestamp, timestamp + 1)) { // 如果不+1 就会变为普通的 atomicreference
                                    System.out.println("余额小于20元，充值成功，余额：" + money.getReference() + "元");
                                    break;
                                }

                            } else {
                                System.out.println("余额大于20，不需要充值");
                                break;
                            }
                        }
                    }

                }
            }.start();
        }

        // 用户消费线程 模拟消费行为
        new Thread() {
            @Override
            public void run() {

                for (int i = 0; i < 100; i++) {
                    while (true) {
                        int stamp = money.getStamp();
                        Integer m = money.getReference();
                        if (m > 10) {
                            System.out.println("大于10元");
                            if (money.compareAndSet(m, m - 10, stamp, stamp + 1)) { // 如果不+1 就会变为普通的 atomicreference
                                System.out.println("成功消费10元，余额：" + money.getReference());
                                break;
                            }
                        } else {
                            System.out.println("没有足够的金额");
                            break;
                        }
                    }
                    try {
                        Thread.sleep(100);

                    } catch (InterruptedException e) {

                    }
                }

            }
        }.start();



    }


}
