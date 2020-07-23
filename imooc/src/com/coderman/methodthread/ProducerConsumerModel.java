package com.coderman.methodthread;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @Author zhangyukang
 * @Date 2020/6/26 17:34
 * @Version 1.0
 **/
public class ProducerConsumerModel {
    public static void main(String[] args) {
        MyContainer myContainer = new MyContainer();
        for (int j = 0; j < 10; j++) {
            new Thread(() -> {
                for (int i = 0; i < 10; i++) {
                    myContainer.put(i + UUID.randomUUID().toString().substring(0, 5));
                }
            }).start();
        }
        for (int j = 0; j < 10; j++) {
            new Thread(() -> {
                for (int i = 0; i < 10; i++) {
                    myContainer.take();
                }
            }).start();
        }

    }
}

class MyContainer {
    private int maxSize;
    private int size;
    private List<String> storage;

    public MyContainer() {
        this.maxSize = 5;
        this.size = 0;
        this.storage = new ArrayList<>();
    }

    public synchronized void put(String string) {
        while (this.size == maxSize) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.storage.add(string);
        System.out.println(Thread.currentThread().getName() + "生产 " + string + " size=" + (++this.size) + "个");
        this.notifyAll();
    }

    public synchronized String take() {
        while (this.size == 0) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        String string = this.storage.get(0);
        this.storage.remove(0);
        System.out.println(Thread.currentThread().getName() + "消费 " + string + " size=" + (--this.size) + "个");
        this.notifyAll();
        return string;
    }
}
