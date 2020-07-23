package com.coderman.lock.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 使用tryLock()解决死锁的问题
 * 格式:
 * if(lock.tryLock()){
 *     try{
 *         .....
 *     }finally{
 *        lock.unlock();
 *     }
 * }
 * @Author zhangyukang
 * @Date 2020/7/14 12:13
 * @Version 1.0
 **/
public class Lock003Test {
    public static void main(String[] args) throws InterruptedException {
        Lock lock1 = new ReentrantLock();
        Lock lock2 = new ReentrantLock();

        new Thread(() -> {
            while (true) {
                if (lock1.tryLock()) {
                    try {
                        System.out.println("获取lock1成功");
                        if (lock2.tryLock()) {
                            try {
                                System.out.println("获取lock2成功");
                                System.out.println("do something");
                                break;
                            } finally {
                                lock2.unlock();
                            }
                        } else {
                            System.out.println("获取lock2失败");
                        }
                    } finally {
                        lock1.unlock();
                    }
                } else {
                    System.out.println("获取lock1失败");
                }
            }
        }).start();

        new Thread(() -> {
            while (true) {
                if (lock2.tryLock()) {
                    try {
                        System.out.println("获取lock2成功");
                        if (lock1.tryLock()) {
                            try {
                                System.out.println("获取lock1成功");
                                System.out.println("do something");
                                break;
                            } finally {
                                lock1.unlock();
                            }
                        } else {
                            System.out.println("获取lock1失败");
                        }
                    } finally {
                        lock2.unlock();
                    }
                } else {
                    System.out.println("获取lock2失败");
                }
            }
        }).start();


    }
}
