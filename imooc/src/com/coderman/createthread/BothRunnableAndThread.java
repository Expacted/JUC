package com.coderman.createthread;

/**
 * 同时使用runnable和Thread两种方式实现多线程
 * @Author zhangyukang
 * @Date 2020/6/15 16:34
 * @Version 1.0
 **/
public class BothRunnableAndThread {
    public static void main(String[] args) {
        Thread thread = new Thread(() -> System.out.println("我是来自Runnable的方法")) {
            @Override
            public void run() {
                System.out.println("我是来自Thread的方法");
            }
        };
        thread.start();

    }
}
