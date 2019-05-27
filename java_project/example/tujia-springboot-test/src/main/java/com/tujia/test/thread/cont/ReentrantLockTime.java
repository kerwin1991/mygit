package com.tujia.test.thread.cont;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author xiaopengw
 * @date 2019/5/16
 * @remark 可限时
 */
public class ReentrantLockTime implements Runnable {

    public static final ReentrantLock lock = new ReentrantLock();

    // 公平锁 ：先来线程先获取到锁；非公平锁可能会出现有的线程一直获取不到锁，产生饥饿现象；但公平锁会对性能有影响，一般不会有这样的需求需要使用
    public static final ReentrantLock fairLock = new ReentrantLock(true);


    @Override
    public void run() {

        try {
            // 尝试获得锁 过了等待时间还是获取不到 就会放弃（释放锁申请） 避免无限等待可能导致的死锁
            if (lock.tryLock(5, TimeUnit.SECONDS)) { // 等5s
                Thread.sleep(6000);
            } else {
                // 补救措施。。
                System.out.println("get lock failed");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (lock.isHeldByCurrentThread())
                lock.unlock(); // 如果获取锁失败，释放锁 抛异常 IllegalMonitorStateException ，因此需要判断isHeldByCurrentThread
        }

    }


    public static void main(String[] args) {
        ReentrantLockTime time  = new ReentrantLockTime();
        Thread t1 = new Thread(time);
        Thread t2 = new Thread(time);
        t1.start();
        t2.start();
    }

}
