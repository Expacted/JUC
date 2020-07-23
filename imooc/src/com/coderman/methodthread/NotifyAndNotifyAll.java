package com.coderman.methodthread;

/**
 * notify()和notifyAll()的区别
 * 1. 线程1和线程2进入wait状态， 线程3使用notify()和notifyAll()的区别
 *
 * @Author zhangyukang
 * @Date 2020/6/19 21:45
 * @Version 1.0
 **/
public class NotifyAndNotifyAll {

    static Object lock = new Object();

    public static void main(String[] args) throws InterruptedException {

        new Thread(() -> {
            synchronized (lock) {
                System.out.println(Thread.currentThread().getName() + "进入代码块");
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    System.out.println(Thread.currentThread().getName() + "获取锁");
                }
            }
        }, "Thread1").start();

        new Thread(() -> {
            synchronized (lock) {
                System.out.println(Thread.currentThread().getName() + "进入代码块");
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    System.out.println(Thread.currentThread().getName() + "获取锁");
                }
            }
        }, "Thread2").start();

        Thread.sleep(1000);//确保上面的两个线程都进入wait()状态

        new Thread(() -> {
            synchronized (lock) {
//                lock.notify();
//                System.out.println(Thread.currentThread().getName()+"调用notify()唤醒");
                lock.notifyAll();
                System.out.println(Thread.currentThread().getName() + "调用notifyAll()唤醒");
            }
        }, "Thread3").start();

    }
}
