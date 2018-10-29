package com.tujia.test.thread;

/**
 * @author xiaopengw
 * @date 2018/9/21
 * @remark 线程优先级
 */
public class PriorityDemo {


    public static void main(String[] args) {
        HightPriority high = new HightPriority();
        LowPriority low = new LowPriority();
        // 设置线程优先级
        high.setPriority(Thread.MAX_PRIORITY);
        low.setPriority(Thread.MIN_PRIORITY);
        // 开启线程
        low.start();
        high.start();
    }




    public static class HightPriority extends Thread {
        static int count = 0;

        @Override
        public void run() {
            while (true) {
                synchronized (PriorityDemo.class) {
                    count ++;
                    if (count > 10000000) {
                        System.out.println("HightPriority is complete");
                        break;
                    }
                }
            }
        }
    }

    public static class LowPriority extends Thread {
        static int count = 0;

        @Override
        public void run() {
            while (true) {
                synchronized (PriorityDemo.class) {
                    count ++;
                    if (count > 10000000) {
                        System.out.println("LowPriority is complete");
                        break;
                    }
                }
            }
        }
    }

    /**
     * synchronized 在虚拟机内部实现
     * 指定加锁对象
     * 作用于普通方法
     * 作用于静态方法
     *
     *
     *
     */

}
