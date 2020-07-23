package com.coderman.jmm;

/**
 *
 * 可见性问题
 * @Author zhangyukang
 * @Date 2020/7/2 15:51
 * @Version 1.0
 **/
public class VisibilityTest{

    public static /*volatile*/ boolean flag=false;

    public static void main(String[] args)  {

        new Thread(()->{
            while (true){
                if(flag){
                    break;
                }
            }
            System.out.println("thread1 stopped");
        },"线程1").start();

        //线程2用来修改flag的值
        new Thread(()->{
            try {
                //此处睡眠是为了保证,线程一已经将 flag=false 读取到自己的工作内存
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            flag=true;
            System.out.println("线程二修改flag的值为 true");
        },"线程2").start();

    }
}
