package com.tujia.test.thread.cas;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * @author xiaopengw
 * @date 2019/5/14
 * @remark
 */
public class AtomicIntegerFieldUpdaterDemo {
    /*
     * AtomicIntegerFieldUpdater 概述：让普通变量也享受原子操作。成员变量，期初定义为int，后面开发中，希望cas操作，可以不修改原数据类型 使得它是原子操作
     * 作用：用尽量少的代码改动量，实现普通变量的原子操作。
     */
    public static class Candidate {
        int id;
        volatile int score;
    }

    public static final AtomicIntegerFieldUpdater<Candidate> scoreUpdater = AtomicIntegerFieldUpdater
            .newUpdater(Candidate.class, "score");

    // 用于对比 比较 使用了 AtomicIntegerFieldUpdater 的 score 是否是原子操作
    public static AtomicInteger allScore = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {
        final Candidate stu = new Candidate();
        Thread[] t = new Thread[10000];
        for (int i = 0; i < 10000; i++) {
            t[i] = new Thread() {
                @Override
                public void run() {
                    if (Math.random() > 0.4) {
                        scoreUpdater.incrementAndGet(stu);
                        allScore.incrementAndGet();
                    }
                }
            };
            t[i].start();
        }

        for (int i = 0; i < 10000; i++) {
            t[i].join();
        }

        System.out.println("score=" + stu.score);
        // 对比
        System.out.println("allScore=" + allScore);

    }

}
