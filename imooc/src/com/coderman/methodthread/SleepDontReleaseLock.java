package com.coderman.methodthread;

import java.util.concurrent.TimeUnit;

/**
 * sleep()方法不会释放锁
 * 调用sleep方法后线程处于 TIMED_WAITING 状态,不释放当前对象的锁，不消耗cpu资源
 * @Author zhangyukang
 * @Date 2020/6/27 08:08
 * @Version 1.0
 **/
public class SleepDontReleaseLock {

    public static void main(String[] args) throws InterruptedException {
        Object lock=new Object();
        new Thread(()->{
            synchronized (lock){
                System.out.println(Thread.currentThread().getName()+"获取到了锁");
                try {
                    TimeUnit.SECONDS.sleep(5);//休眠5秒钟
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("同步代码结束");
            }
        }).start();
        Thread.sleep(100);//保证上面的线程先执行
        new Thread(()->{
            synchronized (lock){
                System.out.println(Thread.currentThread().getName()+"获取到了锁");
                System.out.println("do something");
                System.out.println("同步代码结束");
            }
        }).start();
    }

}
