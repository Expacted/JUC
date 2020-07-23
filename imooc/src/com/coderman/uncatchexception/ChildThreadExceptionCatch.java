package com.coderman.uncatchexception;

/**
 * 在子线程中处理线程异常: 不推荐使用如果子线程的逻辑复杂很难捕捉到全部的异常
 *
 * @Author zhangyukang
 * @Date 2020/6/30 10:25
 * @Version 1.0
 **/
public class ChildThreadExceptionCatch {
    public static void main(String[] args) {
        ChildThread2 childThread = new ChildThread2();
        new Thread(childThread, "线程1").start();
        new Thread(childThread, "线程2").start();
        new Thread(childThread, "线程3").start();
    }
}

class ChildThread2 implements Runnable {

    @Override
    public void run() {
        try {
            throw new RuntimeException("出现异常了");
        } catch (RuntimeException e) {
            System.out.println(Thread.currentThread().getName() + "catch 到异常：" + e.getMessage());
        }
    }
}
