package com.coderman.methodthread;

/**
 * obj.wait();: 只释放obj这把锁，不影响其他的锁
 *
 * wait只释放当前monitor
 *
 * @Author zhangyukang
 * @Date 2020/6/19 22:05
 * @Version 1.0
 **/
public class WaitReleaseOwnMonitor {

    private static Object lock1 = new Object();

    private static Object lock2 = new Object();

    public static void main(String[] args) throws InterruptedException {

        new Thread(() -> {
            synchronized (lock1) {
                System.out.println(Thread.currentThread().getName() + " got lock1");
                synchronized (lock2) {
                    System.out.println(Thread.currentThread().getName() + " got lock2");
                    try {
                        lock1.wait();//这里只会释放lock1
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "Thread1").start();

       Thread.sleep(1000);//确保线程一进入wait状态

        new Thread(() -> {
            synchronized (lock1) {
                System.out.println(Thread.currentThread().getName() + " got lock1 do something");
            }
        }, "Thread2").start();

        new Thread(() -> {
            synchronized (lock2) {//这里可能获取lock2，应为Thread1 为wait()状态，且lock2 没有被释放~
                System.out.println(Thread.currentThread().getName() + " got lock2 do something");
            }
        }, "Thread3").start();


//        Thread.sleep(1000);
//        new Thread(()->{
//            synchronized (lock1){
//                lock1.notify();
//            }
//        },"唤醒线程").start();

    }


}
