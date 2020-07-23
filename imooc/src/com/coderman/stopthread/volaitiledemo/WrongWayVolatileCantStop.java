package com.coderman.stopthread.volaitiledemo;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * 使用volatile无法停止线程
 * 生产者速度快，消费者的速度慢，阻塞队列满的情况下
 *
 * @Author zhangyukang
 * @Date 2020/6/16 18:34
 * @Version 1.0
 **/
public class WrongWayVolatileCantStop {
    public static void main(String[] args) throws InterruptedException {
        ArrayBlockingQueue<Integer> arrayBlockingQueue=  new ArrayBlockingQueue<>(10);
        Producer producer = new Producer(arrayBlockingQueue);
        Thread producerThread=new Thread(producer);
        producerThread.start();
        Thread.sleep(1000);

        Consumer consumer=new Consumer(arrayBlockingQueue);

        while (consumer.needMoreNumbers()){
            System.out.println("消费者消费："+consumer.getBlockingDeque().take());
            Thread.sleep(100);
        }
        System.out.println("消费者不需要数据了");
        producer.setCanceled(true);
    }
}

class Producer implements Runnable {

    private volatile boolean canceled=false;

    private ArrayBlockingQueue blockingDeque;

    public Producer(ArrayBlockingQueue blockingDeque) {
        this.blockingDeque = blockingDeque;
    }

    @Override
    public void run() {
        Integer num = 0;
        try {
            while (num < Integer.MAX_VALUE && !canceled) {
                if (num % 100 == 0) {
//                    System.out.println(num + "是100的倍数");
                    System.out.println(num + "放入队列");
                    this.blockingDeque.put(num);
                }
                num++;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            System.out.println("生产者停止");
        }
    }

    public boolean isCanceled() {
        return canceled;
    }

    public void setCanceled(boolean canceled) {
        this.canceled = canceled;
    }
}

class Consumer {

    private ArrayBlockingQueue blockingDeque;

    public Consumer(ArrayBlockingQueue blockingDeque) {
        this.blockingDeque = blockingDeque;
    }

    public boolean needMoreNumbers(){
        if(Math.random()>0.95){
            return false;
        }
        return true;
    }

    public ArrayBlockingQueue getBlockingDeque() {
        return blockingDeque;
    }

    public void setBlockingDeque(ArrayBlockingQueue blockingDeque) {
        this.blockingDeque = blockingDeque;
    }
}
