package com.coderman.queue;

import com.sun.org.apache.xerces.internal.dom.PSVIAttrNSImpl;

import java.lang.reflect.WildcardType;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * 延时队列,时间小任务优先执行
 * @Author zhangyukang
 * @Date 2020/8/6 09:47
 * @Version 1.0
 **/
public class DelayQueueTest {
    private static DelayQueue<MyTask> delayQueue=new DelayQueue<>();

    public static void main(String[] args) throws InterruptedException {
        delayQueue.put(new MyTask(10));
        delayQueue.put(new MyTask(14));
        delayQueue.put(new MyTask(11));
        delayQueue.put(new MyTask(5));
        delayQueue.put(new MyTask(6));
        delayQueue.put(new MyTask(17));


        MyTask take = delayQueue.take();
        System.out.println(take);
    }
}
class MyTask implements Delayed {

    private long time;

    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert((System.currentTimeMillis()+time)-System.currentTimeMillis(),TimeUnit.SECONDS);
    }

    @Override
    public int compareTo(Delayed o) {
        return (int) (getDelay(TimeUnit.SECONDS)-o.getDelay(TimeUnit.SECONDS));
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public MyTask(long time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "MyTask{" +
                "time=" + time +
                '}';
    }
}
