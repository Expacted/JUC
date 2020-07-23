package com.coderman.threadpool;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 也是停止接受新任务，但会中断所有的任务，将线程池状态变为 stop。
 *
 * @Author zhangyukang
 * @Date 2020/7/12 09:43
 * @Version 1.0
 **/
public class ShutdownNowTest {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(6);
        for (int i = 0; i < 100; i++) {
            executorService.execute(new Task());
        }
        List<Runnable> runnables = executorService.shutdownNow();//返回任务队列中的任务
        for (Runnable runnable : runnables) {
            System.out.println(runnable);
        }

        while (!executorService.awaitTermination(1, TimeUnit.SECONDS)) {
            System.out.println("线程还在执行。。。");
        }

        System.out.println(executorService.isShutdown());
    }
}
