package com.coderman.stopthread;

/**
 * @Author zhangyukang
 * @Date 2020/6/16 20:01
 * @Version 1.0
 **/
public class InterruptThreadTest {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread();
        thread.start();
        thread.interrupt();//中断线程
        System.out.println( thread.isInterrupted());//获取中断状态
        System.out.println(thread.interrupted());//获取中断标志并重置 main线程
        System.out.println(Thread.interrupted());//获取中断标志并重置 main线程
        System.out.println(thread.isInterrupted());
        thread.join();
        System.out.println("main thread over~");
    }
}
