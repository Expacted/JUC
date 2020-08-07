package com.coderman.threadpool;

import java.io.IOException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 线程池的几个参数 (7 个)
 * @Author zhangyukang
 * @Date 2020/8/7 11:16
 * @Version 1.0
 **/
public class ThreadPoolTest1 {
    public static void main(String[] args) throws InterruptedException {
        /** corePoolSize*/
        //核心线程数,即在没有任务需要执行的时候线程池的大小,这里需要注意的是：
        //在刚刚创建ThreadPoolExecutor的时候，线程并不会立即启动，而是要等到有任务提交时才会启动,
        //所以没有任务需要执行的时候，线程池的大小不一定是corePoolSize。

        /** maximumPoolSize*/
        //线程池中允许的最大线程数,线程池中的当前线程数目不会超过该值。
        //如果队列中任务已满，并且当前线程个数小于maximumPoolSize，那么会创建新的线程来执行任务。

        /** keepAliveTime */
        //线程空闲时间
        //当线程空闲时间达到keepAliveTime时，线程会退出，直到线程数量=corePoolSize
        //如果allowCoreThreadTimeout=true，则会直到线程数量=0

        /** unit */
        //线程空闲时间的单位

        /** BlockingQueue*/
        //任务队列

        /** RejectedExecutionHandler*/
        //无法提交任务时候的拒绝策略

        ArrayBlockingQueue<Runnable> queue = new ArrayBlockingQueue<>(4);
        ThreadPoolExecutor pool=
                new ThreadPoolExecutor
                        (2,
                                4,
                                60,
                                TimeUnit.SECONDS,
                                queue
                        ,new ThreadPoolExecutor.AbortPolicy());
        for (int i = 0; i < 9; i++) {
            pool.submit(()->{
                System.out.println(Thread.currentThread().getName()+"阻塞");
                try {
                    System.in.read();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
        System.out.println(queue);
        //8 个任务 : 2--> 核心线程 , 4 个等待  2->新创建
        //9 个任务 : 拒绝策略:抛出异常
    }
}
