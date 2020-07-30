package com.coderman.cache;

import com.coderman.cache.compute.Computable;
import com.coderman.cache.compute.ExpensiveFunction;

import javax.swing.*;
import java.util.Map;
import java.util.concurrent.*;

/**
 * 解决线程重复计算的问题,利用future
 * @Author zhangyukang
 * @Date 2020/7/30 10:35
 * @Version 1.0
 **/
public class Cache05<A,V> implements Computable<A,V>{

    private final Map<A, Future<V>> cache=new ConcurrentHashMap<>();

    private final Computable<A,V> computable;

    public Cache05(Computable<A, V> computable) {
        this.computable = computable;
    }

    @Override
    public  V compute(A key) throws Exception {
        Future<V> f = cache.get(key);
        if (f == null) {
            Callable<V> callable = () -> computable.compute(key);
            FutureTask<V> ft = new FutureTask<>(callable);
            f = cache.putIfAbsent(key, ft);
            if(f==null){
                f = ft;
                ft.run();
            }
        }
        V v = null;
        try {
            //计算的时候有可能会出现异常
            v = f.get();
        } catch (InterruptedException | ExecutionException e) {
            System.out.println("出现异常:"+e.getMessage());
            //取消线程任务,发送中断信号
            f.cancel(true);
        }
        return v;
    }

    private final static ScheduledExecutorService service = Executors.newScheduledThreadPool(5);

    //缓存过期功能
    public  V compute(A key,long expire,TimeUnit timeUnit) throws Exception {
        if(expire>0){
            service.schedule(() -> {
                //如果还在计算,取消任务
                Future<V> vFuture = cache.get(key);
                if(vFuture!=null){
                   if(!vFuture.isDone()){
                       vFuture.cancel(true);
                   }
                }
                cache.remove(key);
                System.out.println("过期时间到了,清除缓存:"+key);
            },expire,timeUnit);
        }
        return compute(key);
    }

    public V computeRandomExpire(A key) throws Exception {
        long expire = (long) (Math.random() * 10000);
        return compute(key,expire,TimeUnit.MILLISECONDS);
    }


    public static void main(String[] args) throws Exception {
        ExpensiveFunction function = new ExpensiveFunction();
//        SimpleFunction function = new SimpleFunction();
        Cache05<String, Integer> cache = new Cache05<>(function);
        new Thread(()->{
            try {
                Integer result = cache.computeRandomExpire("10");
                System.out.println(Thread.currentThread().getName()+" result="+result);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(()->{
            try {
                Integer result = cache.compute("10");
                System.out.println(Thread.currentThread().getName()+" result="+result);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }
}
