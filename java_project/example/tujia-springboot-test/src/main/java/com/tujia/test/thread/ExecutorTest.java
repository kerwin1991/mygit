package com.tujia.test.thread;

import com.google.common.util.concurrent.*;
import com.tujia.dto.People;

import java.util.concurrent.Callable;
import java.util.concurrent.Executors;

/**
 * @author xiaopengw
 * @date 2018/9/20
 * @remark
 */
public class ExecutorTest {

    public static void main(String[] args) throws InterruptedException {
        ListeningExecutorService pool = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(10));

        ListenableFuture<String> future = pool.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                System.out.println("11111111111111111111111111");
                Thread.sleep(1000 * 3);
                return "Task done !";
            }
        });

        // 这是什么用法？？？  todo
        ListenableFuture<Integer> future_ = pool.submit(
                () -> {
                    System.out.println("22222222222222222222");
                    return new People("baba", 3).getGender();
                }
        );

        // 这么用需要处理异常，不优雅。   推荐下面的使用方法。
        /*future.addListener(new Runnable() {
            @Override
            public void run() {
                try {
                    String s = future.get();
                    System.out.println(s);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
        }, MoreExecutors.sameThreadExecutor());*/


        System.out.println("-----------------------------------");

        // 监听 future
        Futures.addCallback(future, new FutureCallback<String>() {
            @Override
            public void onSuccess(String result) {
                System.out.println(result);
            }

            @Override
            public void onFailure(Throwable t) {
                t.printStackTrace();
            }
        });
        // 监听 future_
        Futures.addCallback(future_, new FutureCallback<Integer>() {
            @Override
            public void onSuccess(Integer result) {
                System.out.println(result);
            }

            @Override
            public void onFailure(Throwable t) {
                t.printStackTrace();
            }
        });


        Thread.sleep(5*1000);

        pool.shutdown();
    }
}
