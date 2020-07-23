package com.coderman.juc.synchronizedtest;

/**
 * 不能使用null作为锁对象
 *
 * @Author zhangyukang
 * @Date 2020/6/19 16:56
 * @Version 1.0
 **/
public class LockNullTest implements Runnable{

    private Object object;

    public static void main(String[] args) {
        LockNullTest lockNullTest = new LockNullTest();
        Thread thread = new Thread(lockNullTest);
        thread.start();

        Thread thread2 = new Thread(lockNullTest);
        thread2.start();
    }

    @Override
    public void run() {
        synchronized (object){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("do something");
        }
    }
}
