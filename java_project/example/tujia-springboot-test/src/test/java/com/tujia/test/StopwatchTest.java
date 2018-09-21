package com.tujia.test;

import com.google.common.base.Stopwatch;

import java.util.concurrent.TimeUnit;

/**
 * @author xiaopengw
 * @date 2018/8/29
 * @remark
 */
public class StopwatchTest {
    public static void main(String[] args) {
        Stopwatch stopwatch = Stopwatch.createStarted();
        try {
            Thread.sleep(1000L*3);  // 单位是毫秒
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(stopwatch.elapsed(TimeUnit.MILLISECONDS)); // 1000毫秒 = 1 秒
    }
}
