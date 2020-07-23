package com.coderman.threadpool;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @Author zhangyukang
 * @Date 2020/7/12 09:50
 * @Version 1.0
 **/
public class ScheduledThreadPoolTest {
    public static void main(String[] args) {
        ScheduledExecutorService pool = Executors.newScheduledThreadPool(5);
        pool.schedule(new Task(),1, TimeUnit.SECONDS);//延迟1s后执行

        //延迟10s后间隔2s执行
        pool.scheduleAtFixedRate(new Task(),10,2,TimeUnit.SECONDS);
    }
}
