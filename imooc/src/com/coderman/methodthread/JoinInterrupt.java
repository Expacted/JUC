package com.coderman.methodthread;

/**
 * 在使用join的时候打断线程
 *
 *
 * @Author zhangyukang
 * @Date 2020/6/27 09:53
 * @Version 1.0
 **/
public class JoinInterrupt {
    public static void main(String[] args) {
        Thread mainThread = Thread.currentThread();
        Thread thread = new Thread(() -> {
            try {
                System.out.println("main线程调用join时的状态：" + mainThread.getState());
                mainThread.interrupt();
                Thread.sleep(3000);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("子线程结束");
        });
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
            thread.interrupt();
            System.out.println("main线程中断");
            //中断后结束子线程
        }
    }
}
