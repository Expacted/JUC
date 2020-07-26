package com.coderman.flowcontroller;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * CountDownLatch 多等一,一等多综合
 * @Author zhangyukang
 * @Date 2020/7/26 21:55
 * @Version 1.0
 **/
public class CountDownLatchDemo4 {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch downLatch1 = new CountDownLatch(1);
        CountDownLatch downLatch2=new CountDownLatch(5);
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++) {
            executorService.execute(new Task2(downLatch1,downLatch2));
        }
        Thread.sleep(5000);
        System.out.println("==================开始==================");
        downLatch1.countDown();
        downLatch2.await();//等待结束
        System.out.println("==================完成==================");
        executorService.shutdown();
    }
}

class Task2 implements Runnable{

    private CountDownLatch countDownLatch;
    private CountDownLatch countDownLatch2;

    public Task2(CountDownLatch countDownLatch,CountDownLatch countDownLatch2) {
        this.countDownLatch = countDownLatch;
        this.countDownLatch2=countDownLatch2;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+"准备就绪");
        try {
            countDownLatch.await();
            System.out.println(Thread.currentThread().getName()+"执行任务");
            Thread.sleep((long) (Math.random()*5000));
            System.out.println(Thread.currentThread().getName()+"完成任务");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            countDownLatch2.countDown();
        }
    }
}

