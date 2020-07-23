package com.coderman.stopthread;

/**
 * run方法内没有wait和sleep方法时正确停止线程
 *
 * @Author zhangyukang
 * @Date 2020/6/16 16:41
 * @Version 1.0
 **/
public class RightWayStopThreadWithoutSleep implements Runnable{

    public static void main(String[] args) throws InterruptedException {
        Thread thread=new Thread(new RightWayStopThreadWithoutSleep());
        thread.start();
        Thread.sleep(10);
        thread.interrupt();//中断线程
    }

    @Override
    public void run() {
       Integer num=0;
       while (!Thread.currentThread().isInterrupted()&&num<Integer.MAX_VALUE/2){
           if(num%10000==0){
               System.out.println(num+"是10000的倍数");
           }
           num++;
       }
        System.out.println("任务结束");
    }
}
