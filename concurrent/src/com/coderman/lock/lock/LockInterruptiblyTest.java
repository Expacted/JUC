package com.coderman.lock.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Lock 获取锁的期间可以被中断
 * @Author zhangyukang
 * @Date 2020/7/14 20:29
 * @Version 1.0
 **/
public class LockInterruptiblyTest {
    public static void main(String[] args) throws InterruptedException {
        Lock lock=new ReentrantLock();
        Thread thread1 = new Thread(() -> {
            try {
                System.out.println(Thread.currentThread().getName() + "尝试获取锁");
                lock.lockInterruptibly();
                try {
                    Thread.sleep(2000);
                    System.out.println(Thread.currentThread().getName() + "获取到了锁");
                } catch (InterruptedException e) {
                    System.out.println(Thread.currentThread().getName() + "在睡眠的时候被中断");
                } finally {
                    lock.unlock();
                    System.out.println(Thread.currentThread().getName() + "释放到了锁");
                }
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName() + "在获取锁的时候被中断");
            }
        });

        Thread thread2=new Thread(()->{
            try {
                System.out.println(Thread.currentThread().getName()+"尝试获取锁");
                lock.lockInterruptibly();
                try {
                    Thread.sleep(2000);
                    System.out.println(Thread.currentThread().getName()+"获取到了锁");
                }catch (InterruptedException e){
                    System.out.println(Thread.currentThread().getName()+"在睡眠的时候被中断");
                }finally {
                    lock.unlock();
                    System.out.println(Thread.currentThread().getName()+"释放到了锁");
                }
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName()+"在获取锁的时候被中断");
            }
        });

        thread1.start();
        thread2.start();

        Thread.sleep(1000);

        thread1.interrupt();//打断线程

    }
}
