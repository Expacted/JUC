package com.coderman.lock.lock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 双重检查的单例模式
 *
 * @Author zhangyukang
 * @Date 2020/7/31 19:34
 * @Version 1.0
 **/
public class DCLSingletonTest {
    public static void main(String[] args) {
        ExecutorService pool = Executors.newFixedThreadPool(100);
        for (int i = 0; i < 10000; i++) {
            pool.submit(() -> {
//                ClassA instance = ClassA.getInstance();
                ClassB instance = ClassB.getInstance();
                System.out.println(Thread.currentThread().getName() + " : " + instance.hashCode());
            });
        }
        pool.shutdown();
    }
}

//饿汉式
class ClassA {

    private static final ClassA INSTANCE = new ClassA();

    private ClassA() {
    }

    public static ClassA getInstance() {
        return INSTANCE;
    }
}

//懒汉式
class ClassB {

    private volatile static ClassB INSTANCE;

    public static ClassB getInstance() {
        if (INSTANCE == null) {
            synchronized (ClassB.class){
                if(INSTANCE==null){
                    INSTANCE = new ClassB();
                }
            }
        }
        return INSTANCE;
    }
}


