package com.coderman.juc.synchronizedtest;

/**
 * @Author zhangyukang
 * @Date 2020/6/17 12:56
 * @Version 1.0
 **/
public class SynchronizedTest1 implements Runnable{

    public static void main(String[] args) {
        SynchronizedTest1 synchronizedTest1 = new SynchronizedTest1();
        Thread thread1 = new Thread(synchronizedTest1);
        Thread thread2 = new Thread(synchronizedTest1);
        thread1.start();
        thread2.start();
    }

    private static int count=1;


    @Override
    public void run() {
        for(int i=0;i<100;i++){
            try {
                Thread.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (this) {
                System.out.println(Thread.currentThread().getName() + "count=" + this.count++);
            }
        }
    }

}
