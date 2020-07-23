package com.coderman.lock.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 模拟电影院买票,演示公平锁,非公平锁. 使用tryLock后默认是非公平的
 * @Author zhangyukang
 * @Date 2020/7/16 10:44
 * @Version 1.0
 **/
public class CinemaTicket {
    public static void main(String[] args) throws InterruptedException {
        Ticket ticket = new Ticket();
        Thread thread1 = new Thread(() -> {
            for (int i=0;i<10;i++){
                ticket.sale();
            }
        });
        Thread thread2 = new Thread(() -> {
            for (int i=0;i<10;i++){
                ticket.sale();
            }
        });
        Thread thread3 = new Thread(() -> {
            for (int i=0;i<10;i++){
                ticket.sale();
            }
        });
        thread1.start();
        thread2.start();
        thread3.start();

        thread1.join();
        thread2.join();
        thread3.join();

        ticket.show();
    }
}

class Ticket{

    private int[][] tickets=new int[10][10];

    private Lock lock=new ReentrantLock(true);

    private int count=tickets.length*tickets[0].length;

    //买票
    public void sale(){
//        if(lock.tryLock()){
        lock.lock();
            try {
                if(count>0){
                    for(int i=0;i<tickets.length;i++){
                        for(int j=0;j<tickets[0].length;j++){
                            if(tickets[i][j]==0){
                                tickets[i][j]=1;
                                try {
                                    Thread.sleep(1000);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                System.out.println(Thread.currentThread().getName()+"购买 ("+i+","+j+")");
//                            show();
                                count--;
                                return;
                            }
                        }
                    }
                }else{
                    System.out.println("无票可售");
                }
            }finally {
                lock.unlock();
            }
//        }
    }

    //显示电影院的票
    public void show(){
        for(int i=0;i<tickets.length;i++){
            for(int j=0;j<tickets[0].length;j++){
                System.out.print(tickets[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println("剩余:"+count+"张票");
    }
}
