package com.tujia.test.thread;

/**
 * @author xiaopengw
 * @date 2018/9/18
 * @remark
 */
public class ThreadJoin {

    public volatile static int i = 0;


    public static void main(String[] args) throws InterruptedException {
        JoinThread thread = new JoinThread("join thread");
        thread.start();
        thread.join();// 不加这个join，下面的打印，会在join thread 线程执行前执行了
        System.out.println("---执行结束---i=" + i);
    }

    public static class JoinThread extends Thread {
        public JoinThread(String name) {
            super(name);
        }

        @Override
        public void run() {
//            for (i=0;i<1000000;i++);
            for (i = 0; i < 100; i++) {
                System.out.print(i+",");
            }
            System.out.println("stop");
        }
    }


    /**
     *
     * yield：让出cpu资源，重新争取cup资源（时间片），只是给别的线程机会跟自己一起抢。
     * join:等待的含义。希望被新开启的线程执行完，主线程再继续执行。
     *      非静态的，当线程执行完后，虚拟机(C语言)会调用notifyAll 方法，去唤醒等待在当前线程实例上的所有线程。
     *      join 本质就是while(isAlive()){wait()}
     * 因此：建议，在应用程序中，不要在线程实例上使用 wait notify notifyall 方法。它们是Object的方法。
     *      原因：这些方法是会被系统调用的，没有办法保证在线程对象上调用，能够如你所愿的工作。
     * 如果一个线程执行结束了，虚拟机(C语言)（自动）会调用notifyAll方法，唤醒所有等待在当前线程实例上的所有线程。
     *
     *
     */
}
