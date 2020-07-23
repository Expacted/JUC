package com.coderman.createthread;

import java.util.Timer;
import java.util.TimerTask;

/**
 * 定时器创建线程
 *
 * @Author zhangyukang
 * @Date 2020/6/15 16:59
 * @Version 1.0
 **/
public class TimerThreadTest {

    public static void main(String[] args) {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
            }
        }, 1000, 1000);
    }
}
