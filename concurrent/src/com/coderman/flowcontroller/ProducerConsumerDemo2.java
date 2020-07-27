package com.coderman.flowcontroller;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 生产者消费者实现
 *
 * @Author zhangyukang
 * @Date 2020/7/27 21:19
 * @Version 1.0
 **/
public class ProducerConsumerDemo2 {
    public static void main(String[] args) {
        Container container = new Container();
        new Thread(()->{
            for (int i = 0; i < 100; i++) {
                try {
                    container.add("i:"+i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(()->{
            for (int i = 0; i < 100; i++) {
                try {
                    container.get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
class Container{
    private final int MAX_SIZE=5;
    private List<String> store;
    private Lock lock=new ReentrantLock();
    private Condition condition=lock.newCondition();

    public Container(){
        this.store=new ArrayList<>(MAX_SIZE);
    }

    public void add(String obj) throws InterruptedException {
        lock.lock();
        try {
            while (store.size()==MAX_SIZE){
                try {
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            condition.signalAll();
            this.store.add(obj);
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName()+"生产:"+obj+" size:"+this.store.size());
        }finally {
            lock.unlock();
        }
    }

    public void get() throws InterruptedException {
        lock.lock();
        try {
            while (store.size()==0){
                try {
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            condition.signalAll();
            String obj = this.store.remove(0);
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName()+"消费:"+obj+" size:"+this.store.size());
        }finally {
            lock.unlock();
        }
    }
}
