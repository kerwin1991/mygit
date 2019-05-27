package com.tujia.test.thread.cont;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author xiaopengw
 * @date 2019/5/14
 * @remark 可重入
 */
public class ReentrantLockDemo implements Runnable{

    /**
     * 1、可重入
     * 2、可中断
     * 3、可限时
     * 4、公平锁
     */
    public static ReentrantLock lock = new ReentrantLock();

    public static int i = 0;

    @Override
    public void run() {
        for (int j = 0; j < 10000000; j++) {
            lock.lock();
            lock.lock();
            try {
                i++;
            } finally {
                // 需要程序控制什么时候释放锁  synchronize 是虚拟机负责锁的释放
                lock.unlock();
                //lock.unlock(); // 重入锁的特点，lock几次，就要unlock几次
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ReentrantLockDemo demo = new ReentrantLockDemo();
        Thread t1 = new Thread(demo);
        Thread t2 = new Thread(demo);
        t1.start();t2.start();t1.join();t2.join();
        System.out.println(demo.i);
    }

    /**
     * jps
     *
     * jstack 17168 观察线程执行情况  如果lock两次，unlock一次，是不会真正释放锁，会检测到有线程在等待锁 waiting
     */
}
