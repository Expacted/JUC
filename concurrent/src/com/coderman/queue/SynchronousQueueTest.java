package com.coderman.queue;

import org.junit.Test;

import java.util.concurrent.SynchronousQueue;

/**
 * 同步队列,它的容量为0,不存储任务,作用在线程之间直接传递任务
 *
 * @Author zhangyukang
 * @Date 2020/8/6 16:36
 * @Version 1.0
 **/
public class SynchronousQueueTest {

    private static SynchronousQueue<String> queue = new SynchronousQueue<>();

    public static void main(String[] args) {

        new Thread(() -> {
            try {
                Thread.sleep(5000);
                queue.put("task");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();


        new Thread(() -> {
            try {
                String take = queue.take();
                System.out.println(take);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    @Test
    public void testAdd() {
        queue.add("test");//Queue full
    }

    @Test
    public void testOffer() {
        boolean offer = queue.offer("test");
        System.out.println(offer);//false
    }
}
