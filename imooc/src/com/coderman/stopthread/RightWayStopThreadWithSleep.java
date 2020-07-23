package com.coderman.stopthread;

/**
 * 在线程可能阻塞的情况下正确的停止线程
 * @Author zhangyukang
 * @Date 2020/6/16 16:52
 * @Version 1.0
 **/
public class RightWayStopThreadWithSleep {
    public static void main(String[] args) throws InterruptedException {
        Runnable runnable=()->{
            Integer num=0;
            while (num<=300&&!Thread.currentThread().isInterrupted()){
                if(num%100==0){
                    System.out.println(num+"是100的倍数");
                }
                num++;
            }
            try {
                System.out.println("线程 sleep");
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();//在sleep过程中被打断时的异常，线程阻塞过程中任然可以中断线程
            }
        };

        Thread thread = new Thread(runnable);
        thread.start();
        Thread.sleep(500);//此处是为了让线程执行完while，进入阻塞
        thread.interrupt();
    }
}
