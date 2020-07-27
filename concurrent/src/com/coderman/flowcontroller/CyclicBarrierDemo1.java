package com.coderman.flowcontroller;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * CyclicBarrier的使用
 *
 * @Author zhangyukang
 * @Date 2020/7/27 21:50
 * @Version 1.0
 **/
public class CyclicBarrierDemo1 {
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(5, () -> System.out.println("集合完毕,开始出发"));

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                try {
                    System.out.println(Thread.currentThread().getName() + "前往集合");
                    Thread.sleep((long) (Math.random() * 10000));
                    System.out.println(Thread.currentThread().getName() +"到达集合地点,等待其他人到齐");
                    try {
                        cyclicBarrier.await();
                        System.out.println(Thread.currentThread().getName() +"出发了");
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }, "emp"+i).start();
        }
    }
}
