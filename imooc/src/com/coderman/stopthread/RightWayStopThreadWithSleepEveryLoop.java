package com.coderman.stopthread;

/**
 * 在每次迭代的过程中都有阻塞，如何正确的停止线程
 * 不需要在每次的迭代过程中都检查线程的中断状态
 * 原因： 在sleep()过程中，会相应中断
 * @Author zhangyukang
 * @Date 2020/6/16 17:04
 * @Version 1.0
 **/
public class RightWayStopThreadWithSleepEveryLoop implements Runnable {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new RightWayStopThreadWithSleepEveryLoop());
        thread.start();
        Thread.sleep(5000);
        thread.interrupt();
    }

    @Override
    public void run() {
        Integer num = 0;
        try {
            while (num < Integer.MAX_VALUE) {
                if (num % 100 == 0) {
                    System.out.println(num + "是100的倍数");
                }
                Thread.sleep(10);
                num++;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("任务完成");
    }
}
