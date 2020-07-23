package com.coderman.methodthread;

import java.util.concurrent.TimeUnit;

/**
 * 分析join()的源码，写出join的等价代码
 * join方法里调用wait()方法，唤醒是由jvm来做的,每一个线程对象，在执行完run方法之后会调用notifyAll方法
 * @Author zhangyukang
 * @Date 2020/6/27 10:24
 * @Version 1.0
 **/
public class JoinEquivalence {
    public static void main(String[] args) {
        Object lock = new Object();
        Thread thread = new Thread(() -> {
            synchronized (lock) {
                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("thread1 fished ");
            }
        });

        Thread thread2 = new Thread(() -> {
            synchronized (lock) {
                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("thread2 fished ");
            }
        });

        thread.start();
        thread2.start();

        //thread.join();
        //thread2.join();
        //等价写法
        synchronized (thread){
            try {
                thread.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        synchronized (thread2){
            try {
                thread2.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


        System.out.println("main thread fished ");
    }
}
