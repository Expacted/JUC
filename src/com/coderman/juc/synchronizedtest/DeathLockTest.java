package com.coderman.juc.synchronizedtest;

/**
 * @Author zhangyukang
 * @Date 2020/6/19 16:11
 * @Version 1.0
 **/
public class DeathLockTest implements Runnable {
    private static Object lock1 = new Object();
    private static Object lock2 = new Object();

    private int flag ;

    public static void main(String[] args) {
        DeathLockTest deathLockTest1 = new DeathLockTest();
        DeathLockTest deathLockTest2= new DeathLockTest();
        deathLockTest1.flag=1;
        Thread t1 = new Thread(deathLockTest1);
        t1.start();

        deathLockTest2.flag=2;
        Thread t2 = new Thread(deathLockTest2);
        t2.start();
    }

    @Override
    public void run() {
        if (flag == 1) {
            synchronized (lock1) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lock2) {
                }
                System.out.println(Thread.currentThread().getName() + "do something");
            }
        }

        if (flag == 2) {
            synchronized (lock2) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lock1) {
                }
                System.out.println(Thread.currentThread().getName() + "do something");
            }
        }

    }


}
