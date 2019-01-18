package com.tujia.test.thread;

/**
 * @author xiaopengw
 * @date 2018/9/18
 * @remark
 */
public class ThreadDaemon {

    public static void main(String[] args) throws InterruptedException {
        DaemonThread daemonThread = new DaemonThread();
        daemonThread.setDaemon(true); // start 之前设置为守护线程  主线程结束，守护线程也会结束
        daemonThread.start();
        Thread.sleep(2000);
    }

    /**
     * 守护线程：
     * 在后台默默完成一些系统性的服务，比如垃圾回收，JIT线程可以理解为守护线程。
     * 当一个java应用中，只有守护线程，java虚拟机就会自然退出。
     * 为整个系统做支持性服务。
     *
     */
    public static class DaemonThread extends Thread {
        @Override
        public void run() {
            while (true) {
                System.out.println("I am alive!");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
