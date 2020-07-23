package com.coderman.stopthread;

/**
 * @Author zhangyukang
 * @Date 2020/6/16 17:23
 * @Version 1.0
 **/
public class CantInterrupt implements Runnable {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new CantInterrupt());
        thread.start();
        Thread.sleep(5000);
        thread.interrupt();
    }

    @Override
    public void run() {
        Integer num = 0;
        while (!Thread.currentThread().isInterrupted()&&num < Integer.MAX_VALUE) {
            if (num % 100 == 0) {
                System.out.println(num + "是100的倍数");
            }
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            num++;
        }
        System.out.println("任务完成");
    }

}
