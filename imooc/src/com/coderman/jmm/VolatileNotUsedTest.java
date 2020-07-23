package com.coderman.jmm;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 对于count++问题 volatile 关键字不起作用
 * @Author zhangyukang
 * @Date 2020/7/2 16:43
 * @Version 1.0
 **/
public class VolatileNotUsedTest {

    private static /*volatile*/ int count=0;

    private static AtomicInteger realCount=new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {

        Thread one = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                count++;
                realCount.incrementAndGet();
            }
        });

        Thread two = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                count++;
                realCount.incrementAndGet();
            }
        });

        one.start();
        two.start();

        one.join();
        two.join();

        System.out.println("count="+count);
        System.out.println("realCount="+realCount.get());
    }
}
