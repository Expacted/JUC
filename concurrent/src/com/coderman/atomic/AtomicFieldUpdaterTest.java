package com.coderman.atomic;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * 将普通的变量升级
 * 不支持static,变量必须volatile修饰
 * @Author zhangyukang
 * @Date 2020/7/17 09:24
 * @Version 1.0
 **/
public class AtomicFieldUpdaterTest {



    public static void main(String[] args) throws InterruptedException {

        AtomicIntegerFieldUpdater<Number> updater = AtomicIntegerFieldUpdater.newUpdater(Number.class, "count");

        Number number1 = new Number();
        Number number2 = new Number();

        new Thread(()->{
            for (int i = 0; i < 100000; i++) {
                updater.incrementAndGet(number1);
                number2.count++;
            }
        }).start();

        new Thread(()->{
            for (int i = 0; i < 100000; i++) {
                updater.incrementAndGet(number1);
                number2.count++;
            }
        }).start();

        Thread.sleep(2000);
        System.out.println("count1="+number1.count);
        System.out.println("count2="+number2.count);
    }

    static class Number{
         volatile int count;
    }
}

