package com.coderman.cache;

import com.coderman.cache.compute.Computable;
import com.coderman.cache.compute.ExpensiveFunction;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 缩小锁的粒度,但是任然线程不安全
 * (使用)ConcurrentHashMap >>>> 问题:出现线程重复计算的问题
 * @Author zhangyukang
 * @Date 2020/7/30 10:35
 * @Version 1.0
 **/
public class Cache04<A,V> implements Computable<A,V>{

    private final Map<A,V> cache=new ConcurrentHashMap<>();

    private final Computable<A,V> computable;

    public Cache04(Computable<A, V> computable) {
        this.computable = computable;
    }

    @Override
    public  V compute(A arg) throws Exception {
        System.out.println(Thread.currentThread().getName()+" 进入缓存机制");
        V result = cache.get(arg);
        if(result==null){
            result = computable.compute(arg);
            cache.put(arg,result);
        }
        return result;
    }

    public static void main(String[] args) throws Exception {
        ExpensiveFunction function = new ExpensiveFunction();
//        SimpleFunction function = new SimpleFunction();
        Cache04<String, Integer> cache = new Cache04<>(function);
        new Thread(()->{
            try {
                Integer result = cache.compute("10");
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
        new Thread(()->{
            try {
                Integer result = cache.compute("12");
                System.out.println(Thread.currentThread().getName()+" result="+result);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }
}
