package com.coderman.uncatchexception;

/**
 * 通过全局异常处理捕捉异常
 * @Author zhangyukang
 * @Date 2020/6/30 10:35
 * @Version 1.0
 **/
public class ChildThreadExceptionCatchByHandler {
    public static void main(String[] args) {
        MyUncaughtExceptionHandlerImpl myUncaughtExceptionHandler = new MyUncaughtExceptionHandlerImpl();
        ChildThread3 childThread3 = new ChildThread3();

        Thread thread = new Thread(childThread3, "线程一");
        Thread thread1 = new Thread(childThread3, "线程二");
        Thread thread2 = new Thread(childThread3, "线程三");

        //启动线程
        thread.start();
        thread1.start();
        thread2.start();
        //设置每个线程对应的全局异常捕捉器

        thread.setUncaughtExceptionHandler(myUncaughtExceptionHandler);
        thread1.setUncaughtExceptionHandler(myUncaughtExceptionHandler);
        thread2.setUncaughtExceptionHandler(myUncaughtExceptionHandler);
    }
}
class ChildThread3 implements Runnable{
    @Override
    public void run() {
        throw new RuntimeException("出现了黑客攻击异常");
    }
}
