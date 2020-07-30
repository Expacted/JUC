package com.coderman.cache;

import com.coderman.cache.compute.Computable;
import com.coderman.cache.compute.ExpensiveFunction;
import com.coderman.cache.compute.SimpleFunction;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author zhangyukang
 * @Date 2020/7/30 10:35
 * @Version 1.0
 **/
public class Cache02<A,V> implements Computable<A,V>{

    private final Map<A,V> cache=new HashMap<>();

    private final Computable<A,V> computable;

    public Cache02(Computable<A, V> computable) {
        this.computable = computable;
    }

    @Override
    public synchronized V compute(A arg) throws Exception {
        System.out.println("进入缓存机制");
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
        Cache02<String, Integer> cache = new Cache02<>(function);
        Integer result = cache.compute("13");
        System.out.println(result);
        result = cache.compute("13");
        System.out.println(result);
        result = cache.compute("11");
        System.out.println(result);
        result = cache.compute("11");
        System.out.println(result);
    }
}
