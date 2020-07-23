package com.coderman.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 创建单个线程的线程池。 LinkedBlockingQueue
 * @Author zhangyukang
 * @Date 2020/7/12 09:33
 * @Version 1.0
 **/
public class SingleThreadPoolTest {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 100; i++) {
            executorService.execute(new Task());
        }
    }

}
