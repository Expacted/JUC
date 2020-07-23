package com.coderman.lock.lock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁演示非公平锁的情况
 * @Author zhangyukang
 * @Date 2020/7/16 15:36
 * @Version 1.0
 **/
public class ReadWriteLockTest2 {
    public static void main(String[] args) {
        TicketContainer2 ticketContainer = new TicketContainer2();
        new Thread(()->{ticketContainer.write();},"写线程1").start();
        new Thread(()->{ticketContainer.read();},"读线程1").start();
        new Thread(()->{ticketContainer.read();},"读线程2").start();
        new Thread(()->{
            Thread[] threads=new Thread[100];
            for (int i = 0; i < threads.length; i++) {
                threads[i]=new Thread(()->{
                    ticketContainer.read();
                });
            }
            for (int i = 0; i < threads.length; i++) {
                threads[i].start();
            }
        }).start();
        new Thread(()->{ticketContainer.read();},"读线程3").start();
        new Thread(()->{ticketContainer.read();},"读线程4").start();

    }
}

class TicketContainer2{

    private ReentrantReadWriteLock reentrantReadWriteLock=new ReentrantReadWriteLock(false);

    //读锁
    private ReentrantReadWriteLock.ReadLock readLock=reentrantReadWriteLock.readLock();
    //写锁
    private ReentrantReadWriteLock.WriteLock writeLock=reentrantReadWriteLock.writeLock();

    public void read(){
        System.out.println(Thread.currentThread().getName()+"尝试获取读锁");
        readLock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "正在读取数据>>>>>>>>");
        } finally {
            System.out.println(Thread.currentThread().getName()+"读取数据完成");
            readLock.unlock();
        }
    }

    public void write(){
        System.out.println(Thread.currentThread().getName()+"尝试获取写锁");
        writeLock.lock();
        try {
            System.out.println(Thread.currentThread().getName()+"正在写入数据++++++++++");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println(Thread.currentThread().getName()+"写入数据完成");
            writeLock.unlock();
        }
    }
}

