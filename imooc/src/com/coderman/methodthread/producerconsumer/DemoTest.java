package com.coderman.methodthread.producerconsumer;

import java.util.UUID;

/**
 * @Author zhangyukang
 * @Date 2020/7/5 16:27
 * @Version 1.0
 **/
public class DemoTest {
    public static void main(String[] args) {
        MyContainer myContainer = new MyContainer();
        for (int j = 0; j < 10; j++) {
            new Thread(() -> {
                for (int i = 0; i < 100; i++) {
                    myContainer.put(new BaoZhi(i, UUID.randomUUID().toString().substring(0, 10)));
                }
            },"生产者"+j).start();
        }

        for (int j = 0; j < 10; j++) {
            new Thread(() -> {
                for (int i = 0; i < 100; i++) {
                    myContainer.get();
                }
            },"消费者"+j).start();
        }
    }
}
