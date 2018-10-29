package com.tujia.test.thread.sync;

/**
 * @author xiaopengw
 * @date 2018/9/21
 * @remark
 */
public class AddSync2 implements Runnable{

    //static AddSync2 instance = new AddSync2();

    static int i = 0;

    public synchronized void increase() {
        i++;
    }

    @Override
    public void run() {
        for (int j = 0; j < 10000000; j++) {
            increase();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        AddSync2 instance = new AddSync2();
        Thread thread_1 = new Thread(instance);
        Thread thread_2 = new Thread(instance);
        thread_1.start();
        thread_2.start();
        thread_1.join();
        thread_2.join(); // 等线程执行完再打印
        System.out.println(i);
    }
}
