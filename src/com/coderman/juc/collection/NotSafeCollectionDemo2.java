package com.coderman.juc.collection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

/**
 * 不安全容器
 * ArrayList:线程不安全,add(),以及它的所有方法未加锁
 *
 * 解决
 * 1. 在add方法代码块上加锁 synchronized
 * 2. 使用线程安全的容器Vector
 * 3. 使用工具类Collections.synchronizedList()方法转化成线程安全的集合类
 * @Author zhangyukang
 * @Date 2020/5/12 17:41
 * @Version 1.0
 **/
public class NotSafeCollectionDemo2 {
//    public static void main(String[] args) throws InterruptedException {
//        ArrayList<String> arrayList=new ArrayList<>();
//        for (int i=0;i<3;i++){
//            new Thread(()->{
//                String value = UUID.randomUUID().toString().substring(0, 6);
//                arrayList.add(value);
//            },"thread "+i).start();
//        }
//        Thread.sleep(1000);
//        System.out.println(Thread.currentThread().getName()+"==>"+arrayList);
//
//    }

//    public static void main(String[] args) {
//        ArrayList<String> arrayList=new ArrayList<>();
//        for (int i=0;i<3;i++){
//            new Thread(()->{
//                String value = UUID.randomUUID().toString().substring(0, 6);
//                synchronized (NotSafeCollection.class){
//                    arrayList.add(value);
//                }
//                System.out.println(Thread.currentThread().getName()+"==>"+arrayList);
//            },"thread "+i).start();
//        }
//    }
//    public static void main(String[] args) throws InterruptedException {
//        Vector<String> arrayList=new Vector<>();
//        for (int i=0;i<3;i++){
//            new Thread(()->{
//                String value = UUID.randomUUID().toString().substring(0, 6);
//                arrayList.add(value);
//            },"thread "+i).start();
//        }
//        Thread.sleep(2000);
//        System.out.println(Thread.currentThread().getName()+"==>"+arrayList);
//    }
    public static void main(String[] args) throws InterruptedException {
        List<String> arrayList=new ArrayList<>();
        List<String> list= Collections.synchronizedList(arrayList);
        for (int i=0;i<3;i++){
            new Thread(()->{
                String value = UUID.randomUUID().toString().substring(0, 6);
                list.add(value);
            },"thread "+i).start();
        }
        Thread.sleep(1000);
        System.out.println(Thread.currentThread().getName()+"==>"+list);
    }
}
