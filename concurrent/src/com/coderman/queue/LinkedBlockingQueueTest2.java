package com.coderman.queue;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * 使用LinkedBlockingQueue 完成生产者消费者模型
 * @Author zhangyukang
 * @Date 2020/8/6 09:33
 * @Version 1.0
 **/
public class LinkedBlockingQueueTest2 {
    public static void main(String[] args) {
        LinkedBlockingQueue<Integer> container=new LinkedBlockingQueue<>();
        new Thread(()->{
            for (int i = 0; i < 100; i++) {
                try {
                    container.put(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(()->{
            for (int i = 0; i < 100; i++) {
                try {
                   container.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
