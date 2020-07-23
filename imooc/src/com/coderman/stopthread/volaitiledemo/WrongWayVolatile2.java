package com.coderman.stopthread.volaitiledemo;

/**
 * @Author zhangyukang
 * @Date 2020/6/16 18:27
 * @Version 1.0
 **/
public class WrongWayVolatile2 implements Runnable {

    private static volatile boolean canceled = false;

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new WrongWayVolatile2());
        thread.start();
        Thread.sleep(100);
        canceled=true;
//        thread.interrupt();
    }

    @Override
    public void run() {
        try {
            while (true && !canceled) {
                Thread.sleep(10000);
                System.out.println("do code ~");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
