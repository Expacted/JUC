package com.coderman.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 固定线程数的线程池,当任务很多的时候,任务队列无限,会发生OOM异常
 * @Author zhangyukang
 * @Date 2020/7/12 09:56
 * @Version 1.0
 **/
public class FixedThreadPoolOOMTest {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            executorService.execute(new SubThread());
        }
    }
}
class SubThread implements Runnable{

    @Override
    public void run() {
        try {
            Thread.sleep(100000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
