package com.coderman.methodthread;

/**
 * yield 方法让出当前的cpu时间片，不释放锁，并且让出时间片后可能又被cpu调度，导致结果不准确
 * 开发中不经常使用该方法
 * @Author zhangyukang
 * @Date 2020/6/27 10:37
 * @Version 1.0
 **/
public class YieldTest {
    private static int count = 0;

    private static Object lock = new Object();

    public static void main(String[] args) {
        new Thread(() -> {
            while (count < 100) {
                if (count % 2 == 0) {
                    Thread.yield();
                }
                System.out.println(Thread.currentThread().getName() + ":" + count++);
            }
        }).start();

        new Thread(() -> {
            while (count < 100) {
                if (count % 2 == 1) {
                    Thread.yield();
                }
                System.out.println(Thread.currentThread().getName() + ":" + count++);
            }
        }).start();

    }
}
