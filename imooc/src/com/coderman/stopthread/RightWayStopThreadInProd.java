package com.coderman.stopthread;

/**
 * 在生产环境中如何中断线程
 * 在方法中catch()到中断异常,应该向上抛出
 *
 * @Author zhangyukang
 * @Date 2020/6/16 17:35
 * @Version 1.0
 **/
public class RightWayStopThreadInProd implements Runnable {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new RightWayStopThreadInProd());
        thread.start();
        Thread.sleep(1000);
        thread.interrupt();
    }

    @Override
    public void run() {
        try {
            while (true) {
                System.out.println("go");
//            throwInMethod();
                throwOutMethod();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("任务完成");
    }

    private void throwInMethod() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void throwOutMethod() throws InterruptedException {
        Thread.sleep(3000);
    }
}
