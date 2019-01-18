package com.tujia.test.thread.multi;

import cn.hutool.core.collection.CollectionUtil;
import com.google.common.collect.Lists;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author xiaopengw
 * @date 2018/10/29
 * @remark 多线程处理
 */
public class ElongPushTask {

    // 艺龙全量推送线程池
    public static final ThreadPoolExecutor ELONG_PUSH_EXECUTOR = new ThreadPoolExecutor(8, 10, 0L,
            TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(100),
            (new ThreadFactoryBuilder()).setNameFormat("elongHousePushTask-thread-%d").build());

    public static ListeningExecutorService elongPushFuture = MoreExecutors.listeningDecorator(ELONG_PUSH_EXECUTOR);


    public static void main(String[] args) throws Exception {

        List<Integer> unitIds = Lists.newArrayListWithExpectedSize(100);
        for (int i = 0; i < 100; i++) {
            unitIds.add(i);
        }

        List<ListenableFuture<GetPushAfterInfo>> futureList = Lists.newArrayListWithExpectedSize(10);

        List<List<Integer>> lists = Lists.partition(unitIds, 10);

        for (List<Integer> list : lists) {
            futureList.add(ElongPushTask.elongPushFuture.submit(() -> ElongPushTask.push(list)));
        }

        System.out.println("----------------------------------------打印结果----------------------------------------------");
        int success = 0;
        if (CollectionUtil.isNotEmpty(futureList)) {
            for (ListenableFuture<GetPushAfterInfo> future : futureList) {
                GetPushAfterInfo afterInfo = future.get();
                success += afterInfo.getPushSuccessNum();
                System.out.println(success);
            }
        }
        System.out.println(success);


    }

    public static GetPushAfterInfo push(List<Integer> list) {
        GetPushAfterInfo afterInfo = new GetPushAfterInfo();
        try {
            Thread.sleep(1000*2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (Integer i : list) {
            afterInfo.setPushSuccessNum(i + afterInfo.getPushSuccessNum());
        }
        System.out.println(Thread.currentThread().getName() + "---successNum:" + afterInfo.getPushSuccessNum());
        return afterInfo;
    }

}
