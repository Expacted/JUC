package com.coderman.createthread;

/**
 * @Author zhangyukang
 * @Date 2020/6/15 16:36
 * @Version 1.0
 **/
public class RunnableStyle implements Runnable {

    @Override
    public void run() {
        System.out.println("runnable style-----");
    }

    public static void main(String[] args) {
        Thread thread = new Thread(new RunnableStyle());
        thread.start();
    }
}
