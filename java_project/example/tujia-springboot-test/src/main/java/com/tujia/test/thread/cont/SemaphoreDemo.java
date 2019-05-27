package com.tujia.test.thread.cont;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @author xiaopengw
 * @date 2019/5/18
 * @remark 信号量
 */
public class SemaphoreDemo implements Runnable {

    public final Semaphore semaphore = new Semaphore(5); // 许可量

    @Override
    public void run() {

        try {
            semaphore.acquire(2); // 默认 获取一个
            Thread.sleep(2000); // 模拟耗时
            System.out.println(Thread.currentThread().getId() + " is done");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {

            semaphore.release(2); // 释放
        }

    }
    /**
     * Semaphore
     * 信号量：共享锁。允许多个线程进入到临界区，当信号量数量为1时，就相当于一把锁了，其他线程必须等待了
     * 信号量的使用-资源的分配
     */
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(20);
        SemaphoreDemo demo = new SemaphoreDemo();
        for (int i = 0; i < 20; i++) {
            executorService.submit(demo);
        }
    }

}
