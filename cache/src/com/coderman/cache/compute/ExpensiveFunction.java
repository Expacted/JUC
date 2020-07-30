package com.coderman.cache.compute;

import java.util.concurrent.TimeUnit;

/**
 * 耗时计算 (业务类:复杂计算)
 * @Author zhangyukang
 * @Date 2020/7/30 10:33
 * @Version 1.0
 **/
public class ExpensiveFunction implements Computable<String,Integer>{

    @Override
    public Integer compute(String arg) throws Exception {
        TimeUnit.SECONDS.sleep(2);
        System.out.println(Thread.currentThread().getName()+"计算中......");
        return new Integer(arg);
    }
}
