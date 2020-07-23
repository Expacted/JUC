package com.coderman.singleton;

/**
 * 懒汉模式 线程安全 (不推荐使用,效率低下)
 * @Author zhangyukang
 * @Date 2020/7/5 16:01
 * @Version 1.0
 **/
public class Singleton4 {

    private  static Singleton4 INSTANCE;

    private synchronized static Singleton4 getInstance(){
        if(INSTANCE==null){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            INSTANCE=new Singleton4();
        }
        return INSTANCE;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(()->{
                Singleton4 instance = Singleton4.getInstance();
                System.out.println(instance.hashCode());
            }).start();
        }
    }
}
