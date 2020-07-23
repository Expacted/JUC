package com.coderman.singleton;

/**
 * 使用静态内部类单例 推荐使用 线程安全
 * @Author zhangyukang
 * @Date 2020/7/5 16:15
 * @Version 1.0
 **/
public class Singleton6 {

    private static class InstantBuilder{
        private static final Singleton6 INSTANCE=new Singleton6();
    }

    private static Singleton6 getInstance(){
        return InstantBuilder.INSTANCE;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(()->{
                Singleton6 instance = Singleton6.getInstance();
                System.out.println(instance.hashCode());
            }).start();
        }
    }
}
