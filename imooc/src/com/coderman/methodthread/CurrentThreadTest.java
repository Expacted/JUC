package com.coderman.methodthread;

/**
 * currentThread方法:获取当前线程的引用
 * @Author zhangyukang
 * @Date 2020/6/27 10:43
 * @Version 1.0
 **/
public class CurrentThreadTest {
    public static void main(String[] args) {
        Thread thread = Thread.currentThread();//返回当前线程的引用
        System.out.println(thread.getName());
        new Thread(()->{
            System.out.println(Thread.currentThread().getName());
        },"线程1").start();

        new Thread(()->{
            System.out.println(Thread.currentThread().getName());
        },"线程2").start();

    }
}
