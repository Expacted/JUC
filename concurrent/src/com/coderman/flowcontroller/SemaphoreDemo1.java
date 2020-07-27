package com.coderman.flowcontroller;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * 信号量的基本使用 (颁发许可证)
 * @Author zhangyukang
 * @Date 2020/7/27 20:52
 * @Version 1.0
 **/
public class SemaphoreDemo1 {
    public static void main(String[] args) {
        Semaphore semaphore=new Semaphore(3);
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 100; i++) {
            Runnable runnable= () -> {
                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName()+" do task");
                    Thread.sleep((long) (Math.random()*5000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    semaphore.release();
                }
            };
            executorService.execute(runnable);
        }
    }
}
