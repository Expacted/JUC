package com.coderman.flowcontroller;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * CountDownLatch 多等一
 * @Author zhangyukang
 * @Date 2020/7/26 21:55
 * @Version 1.0
 **/
public class CountDownLatchDemo3 {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            executorService.execute(new Task(countDownLatch));
        }
        Thread.sleep(5000);
        System.out.println("==================开始==================");
        countDownLatch.countDown();
        executorService.shutdown();
    }
}

class Task implements Runnable{

    private CountDownLatch countDownLatch;

    public Task(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+" ready");
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+" do task");
    }
}
