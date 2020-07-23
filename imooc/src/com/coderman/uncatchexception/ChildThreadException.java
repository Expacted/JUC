package com.coderman.uncatchexception;

import java.util.logging.Logger;

/**
 * 处理子线程的异常
 * @Author zhangyukang
 * @Date 2020/6/27 12:04
 * @Version 1.0
 **/
public class ChildThreadException {
    public static void main(String[] args) {
        ChildThread childThread = new ChildThread();
        try {
            new Thread(childThread,"线程1").start();
        } catch (Exception e) {
            System.out.println("catch 线程1的异常");
        }
        try {
            new Thread(childThread,"线程2").start();
        } catch (Exception e) {
            System.out.println("catch 线程2的异常");
        }
        try {
            new Thread(childThread,"线程3").start();
        } catch (Exception e) {
            System.out.println("catch 线程3的异常");
        }
    }
}

class ChildThread implements Runnable{
    @Override
    public void run() {
        throw new RuntimeException(Thread.currentThread().getName()+"线程出现异常了!");
    }
}
