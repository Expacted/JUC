package com.coderman.createthread;

/**
 * 1. 线程的方式 ?（两种方式）
 * ）实现Runnable接口
 * ) 继承Thread类
 * @Author zhangyukang
 * @Date 2020/6/15 12:28
 * @Version 1.0
 **/
public class CreateTheadTest {
    public static void main(String[] args) {
        Demo1 demo1 = new Demo1();
        demo1.start();
        new Thread(new Demo2()).start();
    }
}
class Demo1 extends Thread{
    @Override
    public void run() {
        System.out.println("demo1 do something");
    }
}
class Demo2 implements Runnable{

    @Override
    public void run() {
        System.out.println("demo2 do something");
    }
}
