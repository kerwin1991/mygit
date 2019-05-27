package com.tujia.test.thread.cont;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author xiaopengw
 * @date 2019/5/18
 * @remark
 */
public class ReadWriteLockDemo {

    private final ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private final ReentrantReadWriteLock.ReadLock readLock = readWriteLock.readLock();
    private final ReentrantReadWriteLock.WriteLock writeLock = readWriteLock.writeLock();

    // 读写锁
    /**
     * 读-读不互斥：读读之间不阻塞
     * 读-写互斥：读阻塞写，写也会阻塞读
     * 写写互斥：写写阻塞
     */

}
