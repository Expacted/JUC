package com.coderman.queue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * @Author zhangyukang
 * @Date 2020/8/6 08:50
 * @Version 1.0
 **/
public class LinkedBlockingQueueTest {

    private static LinkedBlockingQueue<Integer> linkedBlockingQueue=new LinkedBlockingQueue<>(1);

    //队列满,而offer（）方法是直接返回false
    @Test
    public void testOffer(){
        boolean offer1 = linkedBlockingQueue.offer(1);
        boolean offer2 = linkedBlockingQueue.offer(2);
        System.out.println("offer1="+offer1);
        System.out.println("offer2="+offer2);
    }

    //当超出队列界限的时候，add（）方法是抛出异常让你处理
    @Test
    public void testAdd(){
        boolean add1 = linkedBlockingQueue.add(1);
        boolean add2 = linkedBlockingQueue.add(2);
        System.out.println("add1="+add1);
        System.out.println("add2="+add2);
    }

    //取出队头的元素,并且移除
    @Test
    public void testPoll(){
        linkedBlockingQueue.add(1);
        Integer ele = linkedBlockingQueue.poll();
        System.out.println("ele="+ele);
    }

    //查看队头的元素,不移除
    @Test
    public void testPeek(){
        linkedBlockingQueue.add(1);
        Integer ele = linkedBlockingQueue.peek();
        System.out.println("ele="+ele);
    }

    @Test
    public void testTake(){
        try {
            new Thread(()->{
                for (int i = 0; i < 5; i++) {
                    try {
                        Thread.sleep(1000);
                        System.out.println("i="+i);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                linkedBlockingQueue.add(100);
            }).start();
            Integer take = linkedBlockingQueue.take();
            System.out.println("take="+take);//阻塞方法
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @After
    public void printQueue(){
        System.out.println("linkedBlockingQueue="+linkedBlockingQueue);
    }
}
