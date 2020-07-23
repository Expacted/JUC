package com.coderman.stopthread;

/**
 * 错误的停止线程的方式: 使用stop()方法
 * 会导致线程运行到一半突然停止，没办法完成一个基本单位的操作（造成脏数据）
 * @Author zhangyukang
 * @Date 2020/6/16 18:12
 * @Version 1.0
 **/
public class StopThread implements Runnable{

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new StopThread());
        thread.start();
        Thread.sleep(1000);
        thread.suspend();
    }

    /**
     * 模拟指挥军队
     */
    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println("连队"+i+"领取装备");
            for (int j=0;j<10;j++){
                System.out.println("士兵"+j);
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("连队"+i+"领取装备结束");
        }
    }
}
