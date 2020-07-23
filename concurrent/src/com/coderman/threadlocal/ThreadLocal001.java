package com.coderman.threadlocal;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 实现每一个1000个线程打印当前的时间格式化成 yyyy-MM-dd HH:mm:ss的字符串
 * @Author zhangyukang
 * @Date 2020/7/14 11:02
 * @Version 1.0
 **/
public class ThreadLocal001 implements Runnable{

    private AtomicInteger sec=new AtomicInteger(80);

    public static void main(String[] args) {
        ThreadLocal001 threadLocal001 = new ThreadLocal001();
        for (int i = 0; i < 1000; i++) {
            new Thread(threadLocal001).start();
        }
    }

    /**
     * 传入毫秒数,获取时间
     * @param sec
     */
    public void getTime(long sec){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(1000 * sec);
        System.out.println(Thread.currentThread().getName()+" time is "+simpleDateFormat.format(date));
    }

    @Override
    public void run() {
       getTime(sec.incrementAndGet());
    }
}
