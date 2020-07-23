package com.coderman.juc;

/**
 * @Author zhangyukang
 * @Date 2020/6/4 18:15
 * @Version 1.0
 **/
public class SaleTicketTest {

    public static void main(String[] args) {
        Tick tick = new Tick();
        new Thread(()->{
            for(int i=0;i<50;i++){
                tick.sale();
            }
        }).start();
        new Thread(()->{
            for(int i=0;i<50;i++){
                tick.sale();
            }
        }).start();
    }
}

class Tick{
    private int count=100;

    public  void sale(){
        if(count>0){
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            count--;
            System.out.println(Thread.currentThread().getName() + "sale---> count=" + count);
        }
    }
}
