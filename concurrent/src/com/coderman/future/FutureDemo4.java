package com.coderman.future;

import java.util.concurrent.*;

/**
 * get()的超时方法
 *
 * @Author zhangyukang
 * @Date 2020/7/30 09:29
 * @Version 1.0
 **/
public class FutureDemo4 {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        Future<String> future = executorService.submit(new CallableTask());
        try {
            String result = future.get(1, TimeUnit.SECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            e.printStackTrace();
        } finally {
            executorService.shutdown();
        }
    }
}
