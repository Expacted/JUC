package com.coderman.singleton;

/**
 * 饿汉式 线程安全
 * @Author zhangyukang
 * @Date 2020/7/5 15:58
 * @Version 1.0
 **/
public class Singleton1 {

    private final static Singleton1 INSTANCE=new Singleton1();

    private static Singleton1 getInstance(){
        return INSTANCE;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(()->{
                Singleton1 instance = Singleton1.getInstance();
                System.out.println(instance);
            }).start();
        }
    }
}
