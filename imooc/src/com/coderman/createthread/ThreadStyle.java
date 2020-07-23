package com.coderman.createthread;

/**
 * @Author zhangyukang
 * @Date 2020/6/15 16:36
 * @Version 1.0
 **/
public class ThreadStyle extends Thread{

    public ThreadStyle() {
    }

    public ThreadStyle(Runnable target) {
        super(target);
    }

    @Override
    public void run() {
        System.out.println("thread style------");
    }

    public static void main(String[] args) {
        ThreadStyle threadStyle = new ThreadStyle();
        threadStyle.start();
    }
}
