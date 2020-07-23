package com.coderman.threadpool;

import java.util.concurrent.*;

/**
 * 可暂停的线程池
 *
 * @Author zhangyukang
 * @Date 2020/7/12 10:31
 * @Version 1.0
 **/
public class PauseableThreadPoolTest extends ThreadPoolExecutor {

    private volatile boolean isPause;

    public PauseableThreadPoolTest(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }

    public PauseableThreadPoolTest(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory);
    }

    public PauseableThreadPoolTest(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, RejectedExecutionHandler handler) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, handler);
    }

    public PauseableThreadPoolTest(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory, RejectedExecutionHandler handler) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory, handler);
    }

    @Override
    protected void beforeExecute(Thread t, Runnable r) {
        super.beforeExecute(t, r);
        synchronized (this) {
            try {
                while (isPause) {
                    this.wait();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void pause() {
        isPause = true;
    }

    public void resume(){
        synchronized (this){
            isPause=false;
            this.notifyAll();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        PauseableThreadPoolTest pool = new PauseableThreadPoolTest(10, 10, 10L, TimeUnit.SECONDS, new LinkedBlockingDeque<>());
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("我被执行");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        for (int i = 0; i < 1000; i++) {
            pool.execute(runnable);
        }
        Thread.sleep(1500);
        pool.pause();
        System.out.println("我被暂停---------------");
        Thread.sleep(1500);
        pool.resume();
        System.out.println("我被恢复---------------");
    }
}
