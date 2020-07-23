package com.coderman.stopthread;

/**
 * 在生产环境中如何中断线程
 * 在catch()中调用中断方法
 *
 * @Author zhangyukang
 * @Date 2020/6/16 17:35
 * @Version 1.0
 **/
public class RightWayStopThreadInProd2 implements Runnable {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new RightWayStopThreadInProd2());
        thread.start();
        Thread.sleep(1000);
        thread.interrupt();
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            System.out.println("go");
//            throwInMethod();
            throwOutMethod();
        }
        System.out.println("任务完成");
    }

    private void throwInMethod() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void throwOutMethod()  {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
        }
    }
}
