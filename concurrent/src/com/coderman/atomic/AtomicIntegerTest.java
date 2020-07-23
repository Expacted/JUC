package com.coderman.atomic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author zhangyukang
 * @Date 2020/7/17 08:16
 * @Version 1.0
 **/
public class AtomicIntegerTest {
    public static AtomicInteger count=new AtomicInteger(0);//原子类

    public static volatile int count2=0;//普通变量

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 100000; i++) {
            executorService.execute(new AddThread());
        }

        Thread.sleep(1000);
        System.out.println("count=" + count);
        System.out.println("count2=" + count2);
    }

}

class AddThread implements Runnable {

//    private static Lock lock=new ReentrantLock();

    @Override
    public void run() {
        /**
         lock.lock();
         try {
         AtomicIntegerTest.count++;
         }finally {
         lock.unlock();
         }
         **/
        AtomicIntegerTest.count.getAndIncrement();
        AtomicIntegerTest.count2++;
    }
}
