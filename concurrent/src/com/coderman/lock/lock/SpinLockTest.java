package com.coderman.lock.lock;

import java.util.concurrent.atomic.AtomicReference;

/**
 * 实现自旋锁 : do-while 循环消耗cpu资源
 * @Author zhangyukang
 * @Date 2020/7/16 21:12
 * @Version 1.0
 **/
public class SpinLockTest {
    public static void main(String[] args) {
        MySpinLock lock = new MySpinLock();
        new Thread(()->{
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName()+" do something");
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }).start();

        new Thread(()->{
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName()+" do something");
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }).start();

    }
}

class MySpinLock{

    private AtomicReference<Thread> atomicReference=new AtomicReference<>();

    public void lock(){
        Thread currentThread = Thread.currentThread();
        while (!atomicReference.compareAndSet(null,currentThread)){
        }
    }
    public void unlock(){
        Thread currentThread = Thread.currentThread();
        atomicReference.compareAndSet(currentThread,null);
    }
}
