package com.coderman.methodthread.producerconsumer;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author zhangyukang
 * @Date 2020/7/5 16:23
 * @Version 1.0
 **/
public class MyContainer {

    private List<BaoZhi> container=new ArrayList<>();

    private static final Integer max=2;

    public synchronized void put(BaoZhi baoZhi){
        while (container.size()==max){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.notifyAll();
        container.add(baoZhi);
        System.out.println(Thread.currentThread().getName()+"生产 "+baoZhi +"size="+container.size());
    }

    public synchronized BaoZhi get(){
        while (container.size()==0){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.notifyAll();
        BaoZhi baoZhi = container.get(0);
        container.remove(baoZhi);
        System.out.println(Thread.currentThread().getName()+"消费 "+baoZhi +"size="+container.size());
        return baoZhi;
    }
}
