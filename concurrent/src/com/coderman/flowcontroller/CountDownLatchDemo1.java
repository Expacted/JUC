package com.coderman.flowcontroller;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

/**
 * CountDownLatch的基本的使用
 * @Author zhangyukang
 * @Date 2020/7/26 21:24
 * @Version 1.0
 **/
public class CountDownLatchDemo1 {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        new Thread(()->{
            System.out.println("线程一开始执行");
            try {
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("线程一开始执行完成");
        }).start();

        Thread.sleep(3000);
        countDownLatch.countDown();
    }
}
