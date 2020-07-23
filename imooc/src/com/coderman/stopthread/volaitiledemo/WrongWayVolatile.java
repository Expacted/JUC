package com.coderman.stopthread.volaitiledemo;

/**
 * @Author zhangyukang
 * @Date 2020/6/16 18:27
 * @Version 1.0
 **/
public class WrongWayVolatile implements Runnable {

    private static volatile boolean canceled = false;

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new WrongWayVolatile());
        thread.start();
        Thread.sleep(3000);
        canceled=true;
    }

    @Override
    public void run() {
        Integer num = 0;
        try {
            while (num < Integer.MAX_VALUE && !canceled) {
                if (num%100==0){
                    System.out.println(num+"是100的倍数");
                }
                Thread.sleep(1000);
                num++;
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
