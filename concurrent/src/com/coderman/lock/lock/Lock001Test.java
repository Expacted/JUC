package com.coderman.lock.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 格式:
 * lock.lock();
 * try{
 *     ....
 * }finally{
 *     lock.unlock();
 * }
 * @Author zhangyukang
 * @Date 2020/7/14 12:13
 * @Version 1.0
 **/
public class Lock001Test {
    private static int count=0;
    public static void main(String[] args) throws InterruptedException {
        Lock lock=new ReentrantLock();
        new Thread(()->{
            for (int i = 0; i < 100000; i++) {
                lock.lock();
                try {
                    count++;
                }finally {
                    lock.unlock();
                }
            }
        }).start();

        new Thread(()->{
            for (int i = 0; i < 100000; i++) {
                lock.lock();
                try {
                    count++;
                }finally {
                    lock.unlock();
                }
            }
        }).start();

        Thread.sleep(1000);
        System.out.println(count);
    }
}
