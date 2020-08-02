package com.coderman.methodthread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 实现一个容器，提供两个方法，add,size。
 * 写两个线程，线程 1 添加 10 个元素到容器中，线程 2 实现监控元素的个数，
 * 当个数到 5 个时，线程 2 给出提示并结束
 *
 * @Author zhangyukang
 * @Date 2020/8/2 16:12
 * @Version 1.0
 **/
public class Demo1<T> {

    //此处加volatile不起作用,保证的引用的可见性,不是对象属性的可见性
    private /*volatile*/ ArrayList<T> list = new ArrayList<>();

    public synchronized void add(T item) {
        list.add(item);
    }

    public synchronized int size() {
        return list.size();
    }

    public static void main(String[] args) throws InterruptedException {
        Demo1<Integer> container = new Demo1<>();

        new Thread(() -> {
            synchronized (container) {
                try {
                    //先保证监视先wait()
                    container.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    container.notify();//唤醒添加线程
                    System.out.println("monitor-thread-end" + " size=" + container.size());
                }
            }
        }, "monitor-thread").start();

        TimeUnit.SECONDS.sleep(1);

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                synchronized (container) {
                    if (container.size() == 5) {
                        container.notify();//唤醒监视该线程
                        try {
                            container.wait();//该线程阻塞
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    container.add(i);
                    System.out.println("add-thread add number:" + i + " size=" + container.size());
                }

            }
        }, "add-thread").start();


    }
}

