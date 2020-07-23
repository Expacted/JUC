package com.coderman.methodthread;

/**
 * @Author zhangyukang
 * @Date 2020/6/19 18:36
 * @Version 1.0
 **/
public class WaitMethod2 implements Runnable{

    private static Object lock=new Object();

    public static void main(String[] args) throws InterruptedException {
        WaitMethod2 waitMethod2 = new WaitMethod2();
        Thread thread = new Thread(waitMethod2);
        thread.start();
        Thread.sleep(5000);
        synchronized (lock){
            lock.notify();//notify()方法并不会释放锁，而是执行完之后的同步代码后释放
            System.out.println(Thread.currentThread().getName()+"执行了notify()");
        }
    }

    @Override
    public void run() {
        synchronized (lock){
            System.out.println(Thread.currentThread().getName()+"开始执行");
            try {
                lock.wait();//该锁上的线程等待，释放该锁
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"获取到锁");
        }
    }
}
