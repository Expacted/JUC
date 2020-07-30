package com.coderman.future;

import java.util.concurrent.*;

/**
 * 使用FutureTask获取任务的结果
 * @Author zhangyukang
 * @Date 2020/7/30 09:57
 * @Version 1.0
 **/
public class FutureTaskDemo {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        FutureTask<String> stringFutureTask = new FutureTask<>(new CallableTask());
        executorService.submit(stringFutureTask);
        try {
            System.out.println(stringFutureTask.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }finally {
            executorService.shutdown();
        }
    }
}
