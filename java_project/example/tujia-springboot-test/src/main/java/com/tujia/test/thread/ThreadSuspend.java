package com.tujia.test.thread;

/**
 * @author xiaopengw
 * @date 2018/9/18
 * @remark
 */
public class ThreadSuspend {

    // suspend 不会释放锁  resume 继续执行线程
    // 如果resume发生在suspend之前，发生死锁

    private static final Object o = new Object();
    static SuspendTest t1 = new SuspendTest("thread one");
    static SuspendTest t2 = new SuspendTest("thread two");

    public static void main(String[] args) throws InterruptedException {
        // 看起来 程序是可以征程结束的 因为 resume 都在 suspend 之后执行
        t1.start();
        Thread.sleep(100);
        t2.start();
        t1.resume();
        t2.resume();
        t1.join();
        t2.join();
        // 实际 主线程阻塞，红点点一直在。其实，t2线程的suspend不一定在t2的resume之前执行。t1的由于sleep，一定是
        /*
            jps             查看java.exe的进程id
            jstack pid      查看线程(dump 线程)
        */
        // 发现线程2还在，并且状态是runnable
    }



    public static class SuspendTest extends Thread {

        public SuspendTest(String name) {
            super(name);
        }

        @Override
        public void run() {
            synchronized (o) {
                System.out.println("in: " + getName());
                Thread.currentThread().suspend(); // [1]. 挂起 不释放锁
            }
        }
    }

}
