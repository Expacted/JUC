package com.coderman.stopthread.volaitiledemo;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * 用中断修复volatile无法中断线程的方式
 * @Author zhangyukang
 * @Date 2020/6/16 18:27
 * @Version 1.0
 **/
public class WrongWayVolatileFix {
    public static void main(String[] args) throws InterruptedException {
        ArrayBlockingQueue arrayBlockingQueue=  new ArrayBlockingQueue(10);
        Producer2 producer = new Producer2(arrayBlockingQueue);
        Thread producerThread=new Thread(producer);
        producerThread.start();
        Thread.sleep(1000);

        Consumer2 consumer=new Consumer2(arrayBlockingQueue);

        while (consumer.needMoreNumbers()){
            System.out.println("消费者消费："+consumer.getBlockingDeque().take());
            Thread.sleep(100);
        }
        System.out.println("消费者不需要数据了");
        producerThread.interrupt();
    }
}

class Producer2 implements Runnable {


    private ArrayBlockingQueue blockingDeque;

    public Producer2(ArrayBlockingQueue blockingDeque) {
        this.blockingDeque = blockingDeque;
    }

    @Override
    public void run() {
        Integer num = 0;
        try {
            while (num < Integer.MAX_VALUE&&!Thread.currentThread().isInterrupted()) {
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

}

class Consumer2 {

    private ArrayBlockingQueue blockingDeque;

    public Consumer2(ArrayBlockingQueue blockingDeque) {
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
