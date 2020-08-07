package com.coderman.methodthread;

/**
 * 两个线程,一个打印A-Z,一个打印a-z,两个线程交替打印
 * @Author zhangyukang
 * @Date 2020/8/2 15:57
 * @Version 1.0
 **/
public class PrinterTest {
    public static void main(String[] args) throws InterruptedException {
        Object lock=new Object();
        Thread thread1 = new Thread(() -> {
            for (char i = 'a'; i <= 'z'; i++) {
                synchronized (lock) {
                    lock.notify();
                    System.out.print(i);
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            //确保唤醒最后一个线程,顺利结束
            synchronized (lock){
                lock.notify();
            }
        });

        Thread thread2 = new Thread(() -> {
            for (char i = 'A'; i <= 'Z'; i++) {
                synchronized (lock) {
                    lock.notify();
                    System.out.print(i);
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            //确保唤醒最后一个线程,顺利结束
            synchronized (lock){
                lock.notify();
            }
        });

        thread1.start();
        thread2.start();

    }
}
