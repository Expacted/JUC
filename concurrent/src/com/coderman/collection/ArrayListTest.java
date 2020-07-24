package com.coderman.collection;

import java.util.ArrayList;
import java.util.List;

/**
 * ArrayList线程不安全问题
 * @Author zhangyukang
 * @Date 2020/7/24 11:08
 * @Version 1.0
 **/
public class ArrayListTest {
    public static void main(String[] args) throws InterruptedException {
        List<Integer> list=new ArrayList<>();
        Thread t1=new Thread(()->{
            for (int i = 1; i <=10000; i++) {
                list.add(i);
            }
        });
        Thread t2=new Thread(()->{
            for (int i = 10001; i <=20000; i++) {
                list.add(i);
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();
        System.out.println(list.size());
    }
}
