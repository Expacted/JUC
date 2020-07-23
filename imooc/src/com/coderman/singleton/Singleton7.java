package com.coderman.singleton;

/**
 * 枚举单例
 * @Author zhangyukang
 * @Date 2020/7/5 16:18
 * @Version 1.0
 **/
public enum  Singleton7 {
    INSTANCE;

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(()->{
                System.out.println(Singleton7.INSTANCE.hashCode());
            }).start();
        }
    }
}
