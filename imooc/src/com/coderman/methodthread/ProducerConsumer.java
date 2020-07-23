package com.coderman.methodthread;

import java.util.LinkedList;

/**
 * 生产者消费者模式
 *
 * @Author zhangyukang
 * @Date 2020/6/19 22:35
 * @Version 1.0
 **/
public class ProducerConsumer {

    private static Container container = new Container();


    public static void main(String[] args) {
        for(int j=0;j<20;j++){
            new Thread(()->{
                for(int i=0;i<10;i++){
                    container.add(new Apple("apple:"+i));
                }
            }).start();
        }
        for(int j=0;j<20;j++){
            new Thread(()->{
                for(int i=0;i<10;i++){
                    container.get();
                }
            }).start();
        }

    }
}

class Apple {
    private String id;

    public Apple(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Apple{" +
                "id='" + id + '\'' +
                '}';
    }
}

class Container {
    private int size = 0;
    private static final int MAX_SIZE = 5;
    private LinkedList<Apple> list = new LinkedList<>();



    public synchronized void add(Apple apple) {
        try {
            while (this.size == MAX_SIZE) {
                this.wait();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        list.add(apple);
        this.notifyAll();
        System.out.println(Thread.currentThread().getName() + "生产：" + apple + " size=" + (++this.size));
    }

    public synchronized Apple get() {
        try {
            while (this.size == 0) {
                this.wait();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Apple apple = list.removeFirst();
        this.notifyAll();
        System.out.println(Thread.currentThread().getName() + "消费：" + apple + " size=" + (--this.size));
        return apple;
    }
}




