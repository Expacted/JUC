package com.coderman.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * shutdown() 执行后停止接受新任务，会把队列的任务执行完毕。
 * 优雅的关闭线程池
 *
 * @Author zhangyukang
 * @Date 2020/7/12 09:40
 * @Version 1.0
 **/
public class ShutdownTest {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 100; i++) {
            executorService.execute(new Task());
        }
        executorService.shutdown();
        executorService.execute(new Task());//shutdown之后不能再提交任务
        System.out.println(executorService.isShutdown());
        System.out.println(executorService.isTerminated());
    }
}
