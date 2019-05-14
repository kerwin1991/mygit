package com.tujia.test.thread.sync;

/**
 * @author xiaopengw
 * @date 2019/2/25
 * @remark
 */
public class VisibilityTest extends Thread {

    private /*volatile*/ boolean stop;

    @Override
    public void run() {
        int i = 0;
        while (!stop) {
            i++;
        }
        System.out.println("finish loop, i= " + i);
    }

    public void stopIt() {
        this.stop = true;
    }

    public boolean getStop() {
        return stop;
    }


    public static void main(String[] args) throws InterruptedException {
        VisibilityTest v = new VisibilityTest();
        v.start();

        Thread.sleep(1000);
        v.stopIt();
        Thread.sleep(2000);
        System.out.println("finish main");
        System.out.println(v.getStop());
    }

}
