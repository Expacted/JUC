package com.coderman.sixstates;

/**
 * 线程的状态
 * NEW RUNNABLE Terminated
 * @Author zhangyukang
 * @Date 2020/6/16 20:34
 * @Version 1.0
 **/
public class NewRunnableTerminated implements Runnable{
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new NewRunnableTerminated());//new一个线程： NEW
        Thread.State state = thread.getState();
        System.out.println(state);
        thread.start();
        System.out.println(thread.getState());//启动线程后，等待调度：Runnable
        Thread.sleep(10);
        System.out.println(thread.getState());//线程运行中 Runnable
        Thread.sleep(1000);
        System.out.println(thread.getState());//线程终止 Terminated
    }

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            System.out.println(i);
        }
    }
}
