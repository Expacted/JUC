package com.coderman.cache;

import com.coderman.cache.compute.Computable;
import com.coderman.cache.compute.ExpensiveFunction;
import com.coderman.cache.compute.SimpleFunction;

import java.util.HashMap;
import java.util.Map;

/**
 * 多线程访问的性能问题:在多线程的情况下,只能一个线程进入cache 的计算方法
 * @Author zhangyukang
 * @Date 2020/7/30 10:35
 * @Version 1.0
 **/
public class Cache03<A,V> implements Computable<A,V>{

    private final Map<A,V> cache=new HashMap<>();

    private final Computable<A,V> computable;

    public Cache03(Computable<A, V> computable) {
        this.computable = computable;
    }

    @Override
    public synchronized V compute(A arg) throws Exception {
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
        Cache03<String, Integer> cache = new Cache03<>(function);
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
                Integer result = cache.compute("11");
                System.out.println(Thread.currentThread().getName()+" result="+result);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }
}
