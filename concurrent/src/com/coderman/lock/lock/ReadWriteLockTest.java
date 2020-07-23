package com.coderman.lock.lock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读锁可以同时获取,因此读线程可以同时进行,但是读和写是不能同时进行的.
 * 并且默认是采用公平锁的方式,不可插队.
 * 读写锁的测试
 * @Author zhangyukang
 * @Date 2020/7/16 11:03
 * @Version 1.0
 **/
public class ReadWriteLockTest {
    public static void main(String[] args) {
        TicketContainer ticketContainer = new TicketContainer();
        new Thread(()->{ticketContainer.read();},"readThread1").start();
        new Thread(()->{ticketContainer.read();},"readThread2").start();
        new Thread(()->{ticketContainer.read();},"readThread3").start();
        new Thread(()->{ticketContainer.read();},"readThread4").start();
        new Thread(()->{ticketContainer.write();},"writeThread1").start();
        new Thread(()->{ticketContainer.write();},"writeThread2").start();
        new Thread(()->{ticketContainer.read();},"readThread5=====").start();
        new Thread(()->{ticketContainer.read();},"readThread6=====").start();
        new Thread(()->{ticketContainer.write();},"writeThread3").start();
        new Thread(()->{ticketContainer.write();},"writeThread4").start();
    }
}

class TicketContainer{

    private ReentrantReadWriteLock reentrantReadWriteLock=new ReentrantReadWriteLock();

    //读锁
    private ReentrantReadWriteLock.ReadLock readLock=reentrantReadWriteLock.readLock();
    //写锁
    private ReentrantReadWriteLock.WriteLock writeLock=reentrantReadWriteLock.writeLock();

    public void read(){
        readLock.lock();
        try {
            System.out.println(Thread.currentThread().getName()+"正在读取数据");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println(Thread.currentThread().getName()+"读取数据完成");
            readLock.unlock();
        }
    }

    public void write(){
        writeLock.lock();
        try {
            System.out.println(Thread.currentThread().getName()+"正在写入数据");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println(Thread.currentThread().getName()+"写入数据完成");
            writeLock.unlock();
        }
    }
}
