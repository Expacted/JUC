package com.coderman.threadlocal;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * simpleDateFormat 作为静态变量出现线程安全的问题
 * 使用线程池
 * 实现每一个1000个线程打印当前的时间格式化成 yyyy-MM-dd HH:mm:ss的字符串
 * @Author zhangyukang
 * @Date 2020/7/14 11:02
 * @Version 1.0
 **/
public class ThreadLocal003 implements Runnable{

    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private AtomicInteger sec=new AtomicInteger(80);

    public static void main(String[] args) throws InterruptedException {
        ThreadLocal003 threadLocal001 = new ThreadLocal003();
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 1000; i++) {
            executorService.execute(threadLocal001);
        }
        Thread.sleep(2000);
        executorService.shutdown();
    }

    /**
     * 传入毫秒数,获取时间
     * @param sec
     */
    public void getTime(long sec){
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(1000 * sec);
        System.out.println(Thread.currentThread().getName()+" time is "+simpleDateFormat.format(date));
    }

    @Override
    public void run() {
       getTime(sec.incrementAndGet());
    }
}
