package com.coderman.juc.synchronizedtest;

/**
 * @Author zhangyukang
 * @Date 2020/6/17 12:56
 * @Version 1.0
 **/
public class SynchronizedTest2 implements Runnable{

    private static SynchronizedTest2 lock1=new SynchronizedTest2();
    private static SynchronizedTest2 lock2=new SynchronizedTest2();

    public static void main(String[] args) {
        SynchronizedTest2 synchronizedTest2 = new SynchronizedTest2();
        Thread t1 = new Thread(synchronizedTest2);
        Thread t2 = new Thread(synchronizedTest2);
        t1.start();
        t2.start();
    }

    @Override
    public void run() {
        synchronized (lock1){
            try {
                System.out.println(Thread.currentThread().getName()+"进入代码块1");
                Thread.sleep(3000);
                System.out.println(Thread.currentThread().getName()+"执行结束1");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        synchronized (lock2){
            try {
                System.out.println(Thread.currentThread().getName()+"进入代码块2");
                Thread.sleep(3000);
                System.out.println(Thread.currentThread().getName()+"执行结束2");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
