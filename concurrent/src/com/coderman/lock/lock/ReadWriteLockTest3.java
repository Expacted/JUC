package com.coderman.lock.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @Author zhangyukang
 * @Date 2020/8/1 21:08
 * @Version 1.0
 **/
public class ReadWriteLockTest3 {

    private static ReentrantLock lock=new ReentrantLock();

    private static ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();

    private static ReentrantReadWriteLock.ReadLock readLock = reentrantReadWriteLock.readLock();

    private static ReentrantReadWriteLock.WriteLock writeLock = reentrantReadWriteLock.writeLock();


    public static void read(Lock lock) {
        lock.lock();
        try {
            System.out.println("read finished");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void write(Lock lock) {
        lock.lock();
        try {
            System.out.println("write finished");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(()->read(readLock)).start();
        }
        for (int i = 0; i < 100; i++) {
            new Thread(()->write(writeLock)).start();
        }
    }
}
