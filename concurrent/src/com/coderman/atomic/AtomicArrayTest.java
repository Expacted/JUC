package com.coderman.atomic;

import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * 原子类数组
 *
 * @Author zhangyukang
 * @Date 2020/7/17 09:08
 * @Version 1.0
 **/
public class AtomicArrayTest {
    private static AtomicIntegerArray array = new AtomicIntegerArray(1000);

    public static void main(String[] args) throws InterruptedException {
        Thread[] thread1 = new Thread[100];
        Thread[] thread2 = new Thread[100];
        for (int i = 0; i < 100; i++) {
            thread1[i] = new Thread(new Incrementer(array));
            thread2[i] = new Thread(new Decrementer(array));
            thread1[i].start();
            thread2[i].start();
        }
        for (int i = 0; i < 100; i++) {
            thread1[i].join();
            thread2[i].join();
        }
        for (int i = 0; i < array.length(); i++) {
            if(array.get(i)!=0){
                throw new RuntimeException("发现了错误! i="+i);
            }else{
                System.out.println(array.get(i));
            }
        }
        System.out.println("运行成功");
    }
}

class Decrementer implements Runnable {

    private AtomicIntegerArray array;

    public Decrementer(AtomicIntegerArray array) {
        this.array = array;
    }

    @Override
    public void run() {
        for (int i = 0; i < array.length(); i++) {
            array.getAndDecrement(i);
        }
    }
}

class Incrementer implements Runnable {

    private AtomicIntegerArray array;

    public Incrementer(AtomicIntegerArray array) {
        this.array = array;
    }

    @Override
    public void run() {
        for (int i = 0; i < array.length(); i++) {
            array.getAndIncrement(i);
        }
    }
}
