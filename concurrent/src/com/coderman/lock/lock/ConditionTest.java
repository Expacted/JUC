package com.coderman.lock.lock;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author zhangyukang
 * @Date 2020/8/6 09:16
 * @Version 1.0
 **/
public class ConditionTest {

    private static final int MAX_SIZE=5;

    private volatile int size=0;

    private List<Integer> container=new LinkedList<>();

    private ReentrantLock lock=new ReentrantLock(true);

    private Condition p=lock.newCondition();

    private Condition c=lock.newCondition();

    public void put(Integer el){
        lock.lock();
        try {
            while (container.size()==MAX_SIZE){
                try {
                    p.await();//这里只让生产者阻塞,不同于Object.wait() :Object对象上所有线程wait
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            container.add(el);
            System.out.println(Thread.currentThread().getName()+"生产"+el+" size="+( ++size));
            c.signalAll();
        }finally {
            lock.unlock();
        }
    }

    public Integer get(){
        lock.lock();
        try {
            while (container.size()==0){
                try {
                    c.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            Integer el = container.remove(0);
            System.out.println(Thread.currentThread().getName()+"消费"+el+" size="+( --size));
            p.signalAll();
            return el;
        }finally {
            lock.unlock();
        }
    }


    public static void main(String[] args) {
        ConditionTest conditionTest = new ConditionTest();
        new Thread(()->{
            for (int i = 0; i < 100; i++) {
                conditionTest.put(i);
            }
        },"生产者").start();

        new Thread(()->{
            for (int i = 0; i < 100; i++) {
                conditionTest.get();
            }
        },"消费者").start();
    }
}
