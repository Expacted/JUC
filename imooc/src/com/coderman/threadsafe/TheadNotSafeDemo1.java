package com.coderman.threadsafe;

/**
 * 线程不安全 count++
 * @Author zhangyukang
 * @Date 2020/6/30 11:04
 * @Version 1.0
 **/
public class TheadNotSafeDemo1 {

    private static int count=0;

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                count++;
            }
        });
        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                count++;
            }
        });
        thread1.start();
        thread2.start();


        thread1.join();
        thread2.join();

        System.out.println("count="+count);
    }
}
