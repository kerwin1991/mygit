package com.tujia.test.thread;

/**
 * @author xiaopengw
 * @date 2018/9/18
 * @remark
 */
public class ThreadInterrupt {


    public static void main(String[] args) throws InterruptedException {
        Thread myTestThread = new Thread(new TestThread(), "myTestThread");
        myTestThread.start();
        Thread.sleep(2500);
        // 1  2  3 配合使用
        myTestThread.interrupt();// 如果线程不判断 只是个通知 不理会 [3].
    }




    static class TestThread implements Runnable{

        private static int i = 0;

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName());
            while (true) {
                if (Thread.currentThread().isInterrupted()) { // [1].
                    System.out.println("---Interrupt!---");
                    break;
                }
                System.out.println("---"+ ++i +"---");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    System.out.println("---Interrupt when sleep !---");
                    // 设置中断状态，抛出异常后会清除中断标记位
                    Thread.currentThread().interrupt();// [2].  此处设置，可以让[1]中检测到中断
                }
                Thread.yield();
            }
        }
    }

}

