package com.tujia.test.thread.cont;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author xiaopengw
 * @date 2019/5/18
 * @remark
 */
public class ReentrantLockCondition implements Runnable {

    public static final ReentrantLock lock = new ReentrantLock();

    public static final Condition condition = lock.newCondition();

    @Override
    public void run() {
        try {
            lock.lock();
            System.out.println(Thread.currentThread().getId() + ":hold lock");
            condition.await(); // 类似 wait
            System.out.println("thead is going on");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ReentrantLockCondition re = new ReentrantLockCondition();
        Thread t = new Thread(re);
        t.start();
        Thread.sleep(2000);
        lock.lock();
        condition.signal(); // 类似 notify
        lock.unlock();



    }


}
