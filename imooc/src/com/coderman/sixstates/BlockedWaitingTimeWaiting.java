package com.coderman.sixstates;

/**
 * 线程的后三种状态
 * Block Waiting TimeWaiting
 * @Author zhangyukang
 * @Date 2020/6/16 20:47
 * @Version 1.0
 **/
public class BlockedWaitingTimeWaiting implements Runnable{

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(new BlockedWaitingTimeWaiting());

        Thread thread2 = new Thread(new BlockedWaitingTimeWaiting());
        thread1.start();
        thread2.start();
        System.out.println(thread2.getState());
        Thread.sleep(70);
        System.out.println(thread1.getState());

        Thread.sleep(1300);
        System.out.println(thread1.getState());
    }

    @Override
    public void run() {
        syn();
    }

    private synchronized void syn(){
        try {
            Thread.sleep(1000);
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
