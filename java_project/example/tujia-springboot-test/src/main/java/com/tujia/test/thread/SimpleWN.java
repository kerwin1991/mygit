package com.tujia.test.thread;

/**
 * @author xiaopengw
 * @date 2019/2/20
 * @remark
 */
public class SimpleWN {
    public static final SimpleWN object = new SimpleWN();


    public static class Thread1 extends Thread{
        @Override
        public void run() {
            synchronized (object) {
                System.out.println(System.currentTimeMillis() + " T1 start");
                try {
                    System.out.println(System.currentTimeMillis() + " T1 wait object ...");
                    object.wait(); // wait 方法必须作用在持有锁对象的方法里
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(System.currentTimeMillis() + " T1 end");
            }
        }
    }

    public static class Thread2 extends Thread{
        @Override
        public void run() {
            synchronized (object) {
                System.out.println(System.currentTimeMillis() + " T2 start. notify one thread");
                object.notify();// notify 方法必须作用在持有锁对象的方法里 随机唤醒一个等待该锁的线程
                // notifyAll 唤醒所有等待在当前对象锁上的线程，使具备争取锁资源的条件
                System.out.println(System.currentTimeMillis() + " T2 end");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        Thread1 thread1 = new Thread1();
        Thread2 thread2 = new Thread2();
        thread1.start();
        thread2.start();
    }

}
