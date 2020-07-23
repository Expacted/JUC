package com.coderman.juc;

/**
 * volatile关键字与内存可见性
 * @Author zhangyukang
 * @Date 2020/5/9 11:23
 * @Version 1.0
 **/
public class VolatileTest {
    public static void main(String[] args) {
        ThreadDemo threadDemo = new ThreadDemo();
        Thread thread = new Thread(threadDemo);
        thread.start();
        while (true){
            if (threadDemo.isFlag()){
                System.out.println("主线程读取到的flag = " + threadDemo.isFlag());
                break;
            }
        }
    }
}

class ThreadDemo implements Runnable{ //这个线程是用来修改flag的值的
    public /*volatile*/ boolean flag = false;

    @Override
    public void run() {
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        flag = true;
        System.out.println("ThreadDemo线程修改后的flag = " + isFlag());
    }

    public boolean isFlag() {
        return flag;
    }
}

