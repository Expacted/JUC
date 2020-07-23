package com.coderman.startthread;

/**
 * 启动线程两次会报错: java.lang.IllegalThreadStateException
 * 线程只能够启动一次，启动的时候会校验如果状态不是 threadState！=0.
 * @Author zhangyukang
 * @Date 2020/6/16 16:14
 * @Version 1.0
 **/
public class StartThreadTwiceTest {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(()->{
            try {
                System.out.println("ddd");
                Thread.sleep(100000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread.start();
        thread.start();
    }
}
