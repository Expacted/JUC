package com.coderman.singleton;

/**
 * 饿汉式 线程安全 第二种写法
 * @Author zhangyukang
 * @Date 2020/7/5 15:58
 * @Version 1.0
 **/
public class Singleton2 {

    private final static Singleton2 INSTANCE;

    static {
        INSTANCE=new Singleton2();
    }

    private static Singleton2 getInstance(){
        return INSTANCE;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(()->{
                Singleton2 instance = Singleton2.getInstance();
                System.out.println(instance);
            }).start();
        }
    }
}
