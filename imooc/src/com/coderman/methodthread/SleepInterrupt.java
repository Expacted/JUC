package com.coderman.methodthread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * sleep方法是可中断的
 *
 * sleep和wait的区别
 *
 *  相同点：
 *       sleep()方法和wait()方法的都可以使线程进入 TIME_WAITING 状态
 *       sleep()方法和wait()方法都可以被中断
 *  不同点：
 *      sleep()方法属于Thread的静态方法，wait()方法属于Object的方法
 *      sleep()方法不释放锁，wait()方法释放锁
 *      sleep()必须指定时间，wait()可以不指定时间
 *      sleep()方法可以在任何地方调用，而wait()方法只能在同步代码块或同步方法中使用(即使用synchronized关键字修饰的)，
 *      目的是防止线程死锁和陷入永久等待；
 *
 * @Author zhangyukang
 * @Date 2020/6/27 08:15
 * @Version 1.0
 **/
public class SleepInterrupt {

    private static Lock lock=new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            lock.lock();
            try {
                try {
                    for(int i=0;i<10;i++){
                        TimeUnit.SECONDS.sleep(1);
                        System.out.println(i);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    System.out.println("线程被中断了！");
                }
            }finally {
                lock.unlock();
            }
        });
        thread.start();
        TimeUnit.SECONDS.sleep(5);
        thread.interrupt();
    }
}
