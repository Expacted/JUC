package com.coderman.methodthread;

/**
 *
 * @Author zhangyukang
 * @Date 2020/6/17 12:29
 * @Version 1.0
 **/
public class WaitMethod implements Runnable{

    public static void main(String[] args) throws InterruptedException {
        WaitMethod waitMethod = new WaitMethod();
        Thread thread = new Thread(waitMethod);
        thread.start();
        Thread.sleep(5000);
        System.out.println("唤醒~");
        synchronized (object){
            object.notifyAll();
        }
    }

    public static Object object=new Object();

    @Override
    public void run() {
        synchronized (this.object){
            try {
                Thread.sleep(1000);
                System.out.println("wait");
                this.object.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
