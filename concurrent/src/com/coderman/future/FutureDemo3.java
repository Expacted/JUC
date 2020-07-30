package com.coderman.future;

import java.util.concurrent.*;

/**
 * @Author zhangyukang
 * @Date 2020/7/30 09:19
 * @Version 1.0
 **/
public class FutureDemo3 {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<String> future = executorService.submit(new ExceptionTask());
        System.out.println(future.isDone());
        String result = null;//在此抛出异常
        try {
            result = future.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            System.out.println(result);
            System.out.println(future.isDone());
        }finally {
            executorService.shutdown();
        }
    }
}
class ExceptionTask implements Callable<String>{
    @Override
    public String call() throws Exception {
        int i=10/0;
        System.out.println(i);
        return "success";
    }
}
