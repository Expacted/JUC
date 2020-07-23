package com.coderman.deathlock;

/**
 * 哲学家进餐问题
 * @Author zhangyukang
 * @Date 2020/7/11 11:13
 * @Version 1.0
 **/
public class DinningPhilosophersTest {
    public static void main(String[] args) {
        Philosopher[] philosophers=new Philosopher[5];
        Chopsticks[] chopsticks=new Chopsticks[5];
        //初始化哲学家
        for (int i = 0; i < 5; i++) {
            chopsticks[i]=new Chopsticks();
        }
        //初始化哲学家
        for (int i = 0; i < 5; i++) {
            philosophers[i]=new Philosopher(chopsticks[i],chopsticks[(i+1)%5]);
        }
        //线程启动
        for (int i = 0; i < 5; i++) {
            new Thread(philosophers[i],"哲学家"+i).start();
        }

    }
}
//筷子类
class Chopsticks{

}
//哲学家
class Philosopher implements Runnable{

    private Chopsticks left;
    private Chopsticks right;

    public Philosopher(){

    }

    public Philosopher(Chopsticks left, Chopsticks right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public void run() {
        while (true){
            System.out.println(Thread.currentThread().getName()+" 思考人生中~");
            synchronized (left){
                System.out.println(Thread.currentThread().getName()+" 拿起左边的筷子");
                synchronized (right){
                    System.out.println(Thread.currentThread().getName()+" 拿起右边的筷子");
                    System.out.println(Thread.currentThread().getName()+" 开始吃饭");
                    System.out.println(Thread.currentThread().getName()+" 放下右边的筷子");
                }
                System.out.println(Thread.currentThread().getName()+" 放下左边的筷子");
            }
        }
    }
}
