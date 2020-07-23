package com.coderman.deathlock;

/**
 * 面试题: 写一个死锁的demo
 * @Author zhangyukang
 * @Date 2020/7/5 16:20
 * @Version 1.0
 **/
public class DeathLockDemo {
    public static void main(String[] args) {
        Object lock1=new Object();
        Object lock2=new Object();
        new Thread(()->{
            synchronized (lock1){
                System.out.println("get lock1");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lock2){
                    System.out.println("do something");
                }
            }
        }).start();

        new Thread(()->{
            synchronized (lock2){
                System.out.println("get lock2");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lock1){
                    System.out.println("do something");
                }
            }
        }).start();

    }
}
