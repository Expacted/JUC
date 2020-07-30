package com.coderman.future;

import java.util.concurrent.*;

/**
 * @Author zhangyukang
 * @Date 2020/7/30 09:09
 * @Version 1.0
 **/
public class FutureDemo2 {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(6);
        try {
            Future<String> future;
            for (int i = 0; i < 5; i++) {
                future= executorService.submit(new CallableTask());
                System.out.println(future.get());
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }finally {
            executorService.shutdown();
        }
    }
}
