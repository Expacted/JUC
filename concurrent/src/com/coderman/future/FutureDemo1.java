package com.coderman.future;

import java.util.concurrent.*;

/**
 * @Author zhangyukang
 * @Date 2020/7/30 09:09
 * @Version 1.0
 **/
public class FutureDemo1 {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        Future<String> future = executorService.submit(new CallableTask());
        try {
            System.out.println(future.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }finally {
            executorService.shutdown();
        }
    }
}

class CallableTask implements Callable<String>{

    @Override
    public String call() throws Exception {
        Thread.sleep(3000);
        return "success";
    }
}
