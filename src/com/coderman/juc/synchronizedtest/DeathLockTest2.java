package com.coderman.juc.synchronizedtest;

/**
 * @Author zhangyukang
 * @Date 2020/6/19 16:11
 * @Version 1.0
 **/
public class DeathLockTest2 {
    public static void main(String[] args) {
        Object lock1 = new Object();
        Object lock2 = new Object();

        new Thread(() -> {
            synchronized (lock1) {
                System.out.println("method1");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lock2) {

                }
                System.out.println("do something");
            }
        }).start();

        new Thread(() -> {
            synchronized (lock2) {
                System.out.println("method2");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lock1) {

                }
                System.out.println("do something");
            }
        }).start();

    }
}
