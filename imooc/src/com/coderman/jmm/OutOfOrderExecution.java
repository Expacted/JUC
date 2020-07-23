package com.coderman.jmm;

import java.util.concurrent.CountDownLatch;

/**
 *
 * 演示重排序
 * 1. a=1;x=b;b=1;y=a; ==> a=1;x=0;b=1;y=1; (x=0,y=1)
 * 2. b=1;y=a;a=1;x=b; ==> b=1;y=0;a=1;x=1; (x=1;y=1)
 * 3. a=1;b=1;x=b;y=a; ==> a=1;b=1;x=1;y=1; (x=1;y=1)
 * 4. x=b;b=1;y=a;a=1; ==> x=0;b=1;y=0;a=1; (x=0;y=0) 发生重排序,在一个线程中指令的顺序发生颠倒,JIT编译器的优化
 * @Author zhangyukang
 * @Date 2020/6/30 14:08
 * @Version 1.0
 **/
public class OutOfOrderExecution {
    private static int x=0,y=0;
    private static int a=0,b=0;

    private static int count=0;

    public static void main(String[] args) throws InterruptedException {

        while (true){
            count++;
            x=0;
            y=0;
            a=0;
            b=0;
            CountDownLatch countDownLatch=new CountDownLatch(1);
            Thread one = new Thread(() -> {
                a = 1;
                try {
                    countDownLatch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                x = b;
            });

            Thread two = new Thread(() -> {
                b = 1;
                try {
                    countDownLatch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                y = a;
            });

            one.start();
            two.start();
            countDownLatch.countDown();
            one.join();
            two.join();

            if (x == 0 && y == 0) {
                System.out.println("第"+count+"次结果"+"x="+x+",y="+y);//说明发生了重排序
                break;
            } else {
                System.out.println("第"+count+"次结果"+"x="+x+",y="+y);
            }
        }

    }
}
