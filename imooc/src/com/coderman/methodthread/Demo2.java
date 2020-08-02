package com.coderman.methodthread;

import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * 实现一个容器，提供两个方法，add,size。
 * 写两个线程，线程 1 添加 10 个元素到容器中，线程 2 实现监控元素的个数，
 * 当个数到 5 个时，线程 2 给出提示并结束
 * (使用CountDownLatch实现)
 * @Author zhangyukang
 * @Date 2020/8/2 16:12
 * @Version 1.0
 **/
public class Demo2<T> {

    //此处加volatile不起作用,保证的引用的可见性,不是对象属性的可见性
    private /*volatile*/ ArrayList<T> list = new ArrayList<>();

    public synchronized void add(T item) {
        list.add(item);
    }

    public synchronized int size() {
        return list.size();
    }

    public static void main(String[] args) throws InterruptedException {
        Demo2<Integer> container = new Demo2<>();
        CountDownLatch latch1=new CountDownLatch(1);
        CountDownLatch latch2=new CountDownLatch(1);

        new Thread(() -> {
            try {
                //先保证monitor线程阻塞
                latch1.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                System.out.println("monitor-thread-end size="+container.size());
                latch2.countDown();
            }
        }, "monitor-thread").start();

        Thread.sleep(1000);

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                if(container.size()==5){
                    latch1.countDown();//让monitor线程向下执行
                    try {
                        latch2.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                container.add(i);
                System.out.println("add-thread add number:" + i + " size=" + container.size());
            }
        }, "add-thread").start();
    }
}

