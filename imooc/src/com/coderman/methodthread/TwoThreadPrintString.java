package com.coderman.methodthread;

/**
 * 两个线程，一个线程打印奇数，一个线程打印偶数，使用wait(),notify()交替打印
 * @Author zhangyukang
 * @Date 2020/6/26 17:43
 * @Version 1.0
 **/
public class TwoThreadPrintString {
    public static void main(String[] args) throws InterruptedException {
        Object lock=new Object();
        //打印奇数的进程
        new Thread(()->{
            synchronized (lock){
                for(int i=0;i<100;i++){
                    if(i%2==0){
                        System.out.println(Thread.currentThread().getName()+"->"+ i);
                        //打印出一个偶数后,唤醒其他线程，当前线程等待
                        lock.notify();
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
                lock.notifyAll();//最后一定有一个线程处于wait()状态，此处是为了让那个等待的线程结束
            }
        }).start();
        Thread.sleep(100);
        //打印偶数的进程
        new Thread(()->{
            synchronized (lock){
                for(int i=0;i<100;i++){
                    if(i%2==1){
                        System.out.println(Thread.currentThread().getName()+"->"+ i);
                        lock.notify();
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
                lock.notifyAll();//同上
            }
        }).start();

    }
}
