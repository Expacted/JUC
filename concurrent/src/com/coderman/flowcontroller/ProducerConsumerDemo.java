package com.coderman.flowcontroller;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 生产者消费者实现
 *
 * @Author zhangyukang
 * @Date 2020/7/27 21:19
 * @Version 1.0
 **/
public class ProducerConsumerDemo {
    public static void main(String[] args) {
        final int MAX_SIZE = 2;
        Queue<String> queue = new PriorityQueue<>(MAX_SIZE);
        for (int m = 0; m < 10; m++) {
            Thread producer = new Thread(() -> {
                for (int i = 0; i < 100; i++) {
                    synchronized (ProducerConsumerDemo.class){
                        while (queue.size()==MAX_SIZE){
                            try {
                                ProducerConsumerDemo.class.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        ProducerConsumerDemo.class.notifyAll();
                        queue.add(i+"");
                        System.out.println(Thread.currentThread().getName()+" 生产:"+i+" size:"+queue.size());
                    }
                }
            }, "producer"+m);
            producer.start();
        }

        for (int m = 0; m < 10; m++) {
            Thread consumer = new Thread(() -> {
                for (int i = 0; i < 100; i++) {
                    synchronized (ProducerConsumerDemo.class) {
                        while (queue.size() == 0) {
                            try {
                                ProducerConsumerDemo.class.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        ProducerConsumerDemo.class.notifyAll();
                        System.out.println(Thread.currentThread().getName() + " 消费:" + queue.poll() + " size:" + queue.size());
                    }
                }
            }, "consumer"+m);
            consumer.start();
        }

    }


}
