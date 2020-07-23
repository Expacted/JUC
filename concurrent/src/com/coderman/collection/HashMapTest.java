package com.coderman.collection;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

/**
 * HashMap的基本使用(线程不安全)
 * @Author zhangyukang
 * @Date 2020/7/21 22:06
 * @Version 1.0
 **/
public class HashMapTest {
    public static void main(String[] args) throws InterruptedException {
                HashMap<Integer, String> map = new HashMap<>();
//        ConcurrentHashMap<Integer,String> map=new ConcurrentHashMap<>();
        Thread t1 = new Thread(() -> {
            for (int i = 1; i <= 1000; i++) {
                map.put(i, "val-");
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 1001; i <= 2000; i++) {
                map.put(i, "val");
            }
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(map.size());
    }
}
