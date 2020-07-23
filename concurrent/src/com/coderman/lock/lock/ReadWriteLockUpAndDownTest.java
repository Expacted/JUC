package com.coderman.lock.lock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁的升降级,可降级,无法升级(进入阻塞状态)
 * @Author zhangyukang
 * @Date 2020/7/16 20:52
 * @Version 1.0
 **/
public class ReadWriteLockUpAndDownTest {

    private static ReentrantReadWriteLock reentrantReadWriteLock=new ReentrantReadWriteLock();

    private static ReentrantReadWriteLock.ReadLock readLock=reentrantReadWriteLock.readLock();

    private static ReentrantReadWriteLock.WriteLock writeLock=reentrantReadWriteLock.writeLock();

    public static void read(){
        readLock.lock();
        try {
            System.out.println(Thread.currentThread().getName()+"获取到了读锁");
            System.out.println(Thread.currentThread().getName()+"升级读锁,线程进入阻塞");
            writeLock.lock();
            System.out.println(Thread.currentThread().getName()+"升级成功");
        }finally {
            System.out.println(Thread.currentThread().getName()+"释放到了读锁");
            readLock.unlock();
        }
    }

    public static void write(){
        writeLock.lock();
        try {
            System.out.println(Thread.currentThread().getName()+"获取到了读锁");
            readLock.lock();
            System.out.println(Thread.currentThread().getName()+"降级成功");
        }finally {
            readLock.unlock();
            System.out.println(Thread.currentThread().getName()+"释放到了读锁");
            writeLock.unlock();
        }
    }


    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        executorService.execute(new Runnable() {
            @Override
            public void run() {
//                read();//无法升级
                write();//锁可以降级
            }
        });
        Thread.sleep(1000);
        executorService.shutdownNow();
    }

}
