package com.tujia.test.thread.cont;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author xiaopengw
 * @date 2019/5/18
 * @remark
 */
public class CountDownLatchDemo implements Runnable {

    private static final CountDownLatch end = new CountDownLatch(10); // 有10个检查任务

    @Override
    public void run() {
        try {
            // 模拟检查任务
            Thread.sleep(new Random().nextInt(10)*1000);
            System.out.println("check complete");
            end.countDown(); // 表示已经完成1个检查  总共完成10个后 主线程开始启动
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        CountDownLatchDemo countDownLatchDemo = new CountDownLatchDemo();
        ExecutorService executorService = Executors.newFixedThreadPool(10); // 开十个线程做检查 等10个线程分别countDown后发射火箭
        for (int i = 0; i < 10; i++) {
            executorService.submit(countDownLatchDemo);
        }
        // 等待检查
        end.await(); // 10 项检查
        // 火箭发射  准备线程执行完以后 通知主线程开始执行
        System.out.println("Fire!!!");
        executorService.shutdown();
    }
}
