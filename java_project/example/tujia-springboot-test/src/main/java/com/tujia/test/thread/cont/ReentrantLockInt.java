package com.tujia.test.thread.cont;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author xiaopengw
 * @date 2019/5/15
 * @remark  可中断
 */
public class ReentrantLockInt implements Runnable {

    public static ReentrantLock lock1 = new ReentrantLock();
    public static ReentrantLock lock2 = new ReentrantLock();
    int lock;

    // 控制加锁顺序
    public ReentrantLockInt(int lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        try {
            if (lock == 1) {
                lock1.lockInterruptibly();
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {}
                lock2.lockInterruptibly();
            } else {

                lock2.lockInterruptibly();
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {}
                lock1.lockInterruptibly();

            }
        } catch (InterruptedException e) {
            e.printStackTrace(); // <1> 线程被中断了，抛出该异常
        } finally {
            if (lock1.isHeldByCurrentThread()) {
                lock1.unlock();
            }
            if (lock2.isHeldByCurrentThread()) {
                lock2.unlock();
            }
            System.out.println(Thread.currentThread().getId() + ":线程退出");
        }
    }


    public static void main(String[] args) throws InterruptedException {
        ReentrantLockInt r1 = new ReentrantLockInt(1);
        ReentrantLockInt r2 = new ReentrantLockInt(2);

        Thread t1 = new Thread(r1);
        Thread t2 = new Thread(r2);

        t1.start();t2.start();
        Thread.sleep(1000);
        // 中断其中一个线程
        DeadlockChecker.check(); // 如果线程被中断 打印 <1> 行异常栈
    }


    /**
     * 可限时：
     *
     *
     *
     */

}
