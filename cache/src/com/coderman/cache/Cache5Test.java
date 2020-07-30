package com.coderman.cache;

import com.coderman.cache.compute.ExpensiveFunction;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 测试缓存
 * @Author zhangyukang
 * @Date 2020/7/30 17:41
 * @Version 1.0
 **/
public class Cache5Test {
    public static void main(String[] args) throws Exception {
        ExecutorService pool = Executors.newFixedThreadPool(1000);
        CountDownLatch countDownLatch=new CountDownLatch(1);
        Cache05<String, Integer> cache = new Cache05<>(new ExpensiveFunction());
        for (int i = 0; i < 1000; i++) {
            pool.submit(() -> {
                try {
                    System.out.println(Thread.currentThread().getName()+"准备中.....");
                    countDownLatch.await();
                    System.out.println(Thread.currentThread().getName()+" 放行:"+TimeHolder.get().format(new Date()));
                    Integer result = cache.compute("666");
                    System.out.println("result="+result);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
        Thread.sleep(5000);
        countDownLatch.countDown();
        pool.shutdown();
    }
}

class TimeHolder{
    private static ThreadLocal<SimpleDateFormat> threadLocal=new ThreadLocal<SimpleDateFormat>(){
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }
    };
    public static SimpleDateFormat get(){
        return threadLocal.get();
    }
}
