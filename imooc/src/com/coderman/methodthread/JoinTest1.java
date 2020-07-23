package com.coderman.methodthread;

import java.util.concurrent.TimeUnit;

/**
 * join()方法的测试
 *
 * @Author zhangyukang
 * @Date 2020/6/27 09:50
 * @Version 1.0
 **/
public class JoinTest1 {
    public static void main(String[] args) throws InterruptedException {
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

        thread.join();
        thread2.join();

        System.out.println("main thread fished ");
    }
}
