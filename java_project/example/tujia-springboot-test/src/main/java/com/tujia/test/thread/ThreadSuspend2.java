package com.tujia.test.thread;

/**
 * @author xiaopengw
 * @date 2018/9/18
 * @remark
 */
public class ThreadSuspend2 {


    public static void main(String[] args) throws InterruptedException {
        SuspendTest thread = new SuspendTest("suspend thread");
        thread.start();
        Thread.sleep(10);
        thread.suspend();
        System.out.println("main thread end...");
    }



    public static class SuspendTest extends Thread {

        private static int i = 0;

        public SuspendTest(String name) {
            super(name);
        }

        @Override
        public void run() {
            while (true) {
                i++;
                /*
                * 如果注释掉，可以打印出主线程的语句 "main thread end..."
                * 如果放开，线程就就看不到 "main thread end..."
                * 这是因为println函数，suspend挂起后的线程没有释放锁，导致其他线程在调用println函数时出现阻塞
                * */
                //System.out.println(i);
            }
        }
    }

    /**
     * （1）独占：因为suspend在调用过程中不会释放所占用的锁，所以如果使用不当会造成对公共对象的独占，使得其他线程无法访问公共对象，严重的话造成死锁。
     * （2）不同步：容易出现因线程暂停导致的数据不同步
     */

}
