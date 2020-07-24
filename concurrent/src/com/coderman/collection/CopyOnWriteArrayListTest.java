package com.coderman.collection;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * CopyOnWriteArrayList 线程安全,同时读,读写操作可以同时进行,在写得时候复制一块内存重新写入,
 * 时候读多写少的情况.
 * @Author zhangyukang
 * @Date 2020/7/24 15:16
 * @Version 1.0
 **/
public class CopyOnWriteArrayListTest {
    public static void main(String[] args) throws InterruptedException {
        List<Integer> list=new CopyOnWriteArrayList<>();
        Thread thread2=new Thread(()->{
            for (int i = 10001; i <=20000; i++) {
                list.add(i);
            }
        });
        Thread thread1=new Thread(()->{
            for (int i = 1; i <=10000; i++) {
                list.add(i);
            }
        });

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();
        System.out.println(list.size());
    }

}
