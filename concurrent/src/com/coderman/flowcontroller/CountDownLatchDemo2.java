package com.coderman.flowcontroller;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * CountDownLatch 一等多 (一个线程去等待多个线程执行完毕,然后再执行自己的任务)
 *  模拟游乐场的过山车,当过山车的位置坐满(10人),开始发车
 * @Author zhangyukang
 * @Date 2020/7/26 21:29
 * @Version 1.0
 **/
public class CountDownLatchDemo2 {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(10);
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 10; i++) {
            int finalI = i+1;
            Runnable runnable = () -> {
                try {
                    Thread.sleep((long) (Math.random()*5000));
                    System.out.println(finalI +"顾客 "+"坐上过山车>>> 准备完成");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    countDownLatch.countDown();
                }
            };
            executorService.execute(runnable);
        }
        //主线程倒计时
        countDownLatch.await();
        System.out.println("============================成功发车============================");
        executorService.shutdown();
    }
}
