package com.coderman.singleton;

/**
 * 懒汉模式 线程不安全
 * @Author zhangyukang
 * @Date 2020/7/5 16:01
 * @Version 1.0
 **/
public class Singleton3 {

    private  static Singleton3 INSTANCE;

    private static Singleton3 getInstance(){
        if(INSTANCE==null){
//            try {
//                Thread.sleep(100);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            INSTANCE=new Singleton3();
        }
        return INSTANCE;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(()->{
                Singleton3 instance = Singleton3.getInstance();
                System.out.println(instance.hashCode());
            }).start();
        }
    }
}
