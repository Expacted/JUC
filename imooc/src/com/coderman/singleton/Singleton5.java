package com.coderman.singleton;

/**
 * 懒汉模式 线程安全(双重锁判断)
 * @Author zhangyukang
 * @Date 2020/7/5 16:01
 * @Version 1.0
 **/
public class Singleton5 {

    //此处是为了在对象初始化的时候发生重排序,从而导致错误
    private volatile static Singleton5 INSTANCE;

    private  static Singleton5 getInstance(){
        if(INSTANCE==null){
            synchronized (Singleton5.class) {
                synchronized (Singleton5.class) {
                    if (INSTANCE == null) {
                        INSTANCE=new Singleton5();
                    }
                }
            }
        }
        return INSTANCE;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(()->{
                Singleton5 instance = Singleton5.getInstance();
                System.out.println(instance.hashCode());
            }).start();
        }
    }
}
