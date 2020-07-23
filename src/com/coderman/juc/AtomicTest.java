package com.coderman.juc;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 理解原子性
 * @Author zhangyukang
 * @Date 2020/5/9 11:48
 * @Version 1.0
 **/
public class AtomicTest {
    public static void main(String[] args){
        AtomicDemo2 atomicDemo = new AtomicDemo2();
        for (int x = 0;x < 40; x++){
            new Thread(atomicDemo,"当前线程:"+x).start();
        }
    }
}

class AtomicDemo implements Runnable{
    private volatile int i = 0;
    public int getI(){
        return i++;
    }
    @Override
    public void run() {
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.print(getI()+",");
    }
}

class AtomicDemo2 implements Runnable{

    //原子变量,可保证原子性,i++步骤是原子性操作
    private AtomicInteger j=new AtomicInteger(0);//

//    private int j=0;

    @Override
    public void run() {
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //互斥锁: 当前时刻只允许一个线程访问
//        synchronized (this){
            System.out.println(Thread.currentThread().getName()+" j="+j.incrementAndGet());
//        }
    }
}
