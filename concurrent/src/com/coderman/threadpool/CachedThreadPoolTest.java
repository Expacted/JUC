package com.coderman.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 无限线程池。 SynchronousQueue
 * @Author zhangyukang
 * @Date 2020/7/12 09:37
 * @Version 1.0
 **/
public class CachedThreadPoolTest {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 100; i++) {
            executorService.execute(new Task());
        }
    }
}
