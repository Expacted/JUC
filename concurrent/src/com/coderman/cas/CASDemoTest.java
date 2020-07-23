package com.coderman.cas;

/**
 * 模拟CAS
 * @Author zhangyukang
 * @Date 2020/7/21 21:30
 * @Version 1.0
 **/
public class CASDemoTest {

    private volatile int value;//内存中的值

    public  synchronized int compareAndSwap(int expectedValue, int newValue){
        int oldValue = value;//获取旧值
        if(oldValue == expectedValue){//如果期望值与当前V位置的值相同就给予新值
            value = newValue;
        }else{
            String name = Thread.currentThread().getName();
            System.out.println(name+"操作未成功");
        }
        return oldValue;//返回V位置原有的值
    }

    public static void main(String[] args) {

        CASDemoTest casDemoTest = new CASDemoTest();
        new Thread(()->{
            casDemoTest.compareAndSwap(0,1);
        },"线程1").start();

        new Thread(()->{
            casDemoTest.compareAndSwap(0,2);
        },"线程2").start();

        new Thread(()->{
            casDemoTest.compareAndSwap(0,3);
        },"线程3").start();

        new Thread(()->{
            casDemoTest.compareAndSwap(0,5);
        },"线程4").start();
    }

}
