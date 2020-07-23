package com.coderman.juc;

/**
 * 线程创建的方式
 * @Author zhangyukang
 * @Date 2020/5/9 11:24
 * @Version 1.0
 **/
public class ThreadCreateTest {
    public static void main(String[] args) {
        new Thread(new Mythread(),"thread 1").start();
        new Mythread2("thread 2").start();
    }
}

class  Mythread implements Runnable{
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+"--->run.....");
    }
}

class  Mythread2 extends Thread{

    Mythread2(String name){
        super(name);
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+"--->run.....");
    }
}
