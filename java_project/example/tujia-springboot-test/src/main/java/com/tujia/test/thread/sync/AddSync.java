package com.tujia.test.thread.sync;

/**
 * @author xiaopengw
 * @date 2018/9/21
 * @remark
 */
public class AddSync implements Runnable{

    static AddSync instance = new AddSync();

    static int i = 0;


    @Override
    public void run() {
        for (int j = 0; j < 10000000; j++) {
            synchronized (instance) {
                i++;
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread_1 = new Thread(instance);
        Thread thread_2 = new Thread(instance);
        thread_1.start();
        thread_2.start();
        thread_1.join();
        thread_2.join(); // 等线程执行完再打印
        System.out.println(i);
    }
}
