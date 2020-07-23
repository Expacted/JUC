package com.coderman.lock.lock;

/**
 * 写一个必然死锁的程序
 * @Author zhangyukang
 * @Date 2020/7/14 12:13
 * @Version 1.0
 **/
public class Lock002Test {
    public static void main(String[] args) throws InterruptedException {
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
