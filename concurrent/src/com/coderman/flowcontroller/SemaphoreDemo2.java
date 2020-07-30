package com.coderman.flowcontroller;

import java.util.concurrent.Semaphore;

/**
 * 这个例子信号量可以类比成饭票
 *
 * @Author zhangyukang
 * @Date 2020/7/27 20:59
 * @Version 1.0
 **/
public class SemaphoreDemo2 {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3);
        for (int i = 0; i < 100; i++) {
            new Thread(new Person(semaphore, "person:" + i)).start();
        }
    }
}

class Person implements Runnable {

    private Semaphore semaphore;

    public Person(Semaphore semaphore, String name) {
        this.semaphore = semaphore;
        this.name = name;
    }

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                '}';
    }

    @Override
    public void run() {
        try {
            semaphore.acquire();
            System.out.println(this.name + "拿到饭票,开始eat~");
            Thread.sleep(1000);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaphore.release();
        }
    }
}
