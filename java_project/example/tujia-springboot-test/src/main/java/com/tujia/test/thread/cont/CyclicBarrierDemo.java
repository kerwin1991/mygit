package com.tujia.test.thread.cont;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author xiaopengw
 * @date 2019/5/18
 * @remark 循环栅栏
 */
public class CyclicBarrierDemo {


    /**
     * cycle表示循环，这个计数器可以反复使用，假如计数器设置为10，那么一批10个线程后，计数器就会归零，然后接着凑齐下一批10个线程
     *
     * 类似CountDownLatch：主线程执行一次。循环栅栏是每执行完特定线程后，执行一次主线程
     *
     */

    public static class Soldier implements Runnable{

        private String soldier;
        private CyclicBarrier cyclicBarrier;

        public Soldier(String soldier, CyclicBarrier cyclicBarrier) {
            this.soldier = soldier;
            this.cyclicBarrier = cyclicBarrier;
        }

        @Override
        public void run() {
            try {
                // 等待所有士兵到齐
                cyclicBarrier.await(); // 第一次等待 等待N个线程全部就位、集合完毕   集合完毕后，[第一次]调用一次栅栏线程 BarrierRun
                doWork();
                // 等待所有士兵完成工作
                cyclicBarrier.await(); // 第二次等待 等待N个线程全部任务执行完毕  全部执行完毕 [第二次]调用一次栅栏线程 BarrierRun
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }

        }

        private void doWork() {
            try {
                Thread.sleep(Math.abs(new Random().nextInt()%10000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(soldier + "：任务执行完成。");

        }
    }

    // 系统触发
    public static class BarrierRun implements Runnable{

        boolean flag;
        int N;

        public BarrierRun(boolean flag, int n) {
            this.flag = flag;
            N = n;
        }

        @Override
        public void run() {
            if (flag) {
                System.out.println("司令，士兵" + N + "个，任务完成");
            } else {
                System.out.println("司令，士兵" + N + "个，集合完毕");
                flag = true;
            }
        }
    }


    public static void main(String[] args) {
        final int N = 10; // 10个士兵
        Thread[] allSoldier = new Thread[N];
        boolean flag = false;
        CyclicBarrier cycle = new CyclicBarrier(N, new BarrierRun(flag, N));
        // 设置屏障点 主要为了执行这个方法
        System.out.println("集合队伍");
        for (int i = 0; i < N; i++) {

            System.out.println("士兵" + i + "报道");
            allSoldier[i] = new Thread(new Soldier("士兵" + i, cycle));
            allSoldier[i].start();
            if (i == 5) {
                allSoldier[0].interrupt();
            }
        }
    }

}
