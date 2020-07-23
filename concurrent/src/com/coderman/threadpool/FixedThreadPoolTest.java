package com.coderman.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 固定线程数量的线程池,使用的线程任务队列:LinkedBlockingQueue
 * @Author zhangyukang
 * @Date 2020/7/11 21:57
 * @Version 1.0
 **/
public class FixedThreadPoolTest {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        for (int i = 0; i < 100; i++) {
            executorService.execute(new Task());
        }
    }
}
class Task implements Runnable{

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+" do task~~");
    }
}
