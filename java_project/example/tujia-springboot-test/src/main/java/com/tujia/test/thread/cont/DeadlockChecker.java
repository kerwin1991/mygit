package com.tujia.test.thread.cont;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

/**
 * @author xiaopengw
 * @date 2019/5/15
 * @remark 检查死锁线程  中断死锁线程
 */
public class DeadlockChecker {

    private static final ThreadMXBean mbean = ManagementFactory.getThreadMXBean();

    final static Runnable deadlockChecker = new Runnable() {
        @Override
        public void run() {
            while (true) {
                long[] deadlockedThreadIds = mbean.findDeadlockedThreads();
                if (deadlockedThreadIds != null) {
                    // 死锁线程
                    ThreadInfo[] threadInfos = mbean.getThreadInfo(deadlockedThreadIds);
                    // 遍历所有线程 遇到死锁线程 中断
                    for (Thread t : Thread.getAllStackTraces().keySet()) {
                        for (int i = 0; i < threadInfos.length; i++) {
                            if (t.getId() == threadInfos[i].getThreadId()) {
                                t.interrupt();
                            }
                        }
                    }
                }
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    //e.printStackTrace();
                }

            }
        }
    };

    public static void check() {
        Thread thread = new Thread(deadlockChecker);
        // 守护线程
        thread.setDaemon(true);
        thread.start();
    }


}
