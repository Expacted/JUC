package com.coderman.cache.compute;

/**
 * 简单计算 (业务类:简单计算)
 * @Author zhangyukang
 * @Date 2020/7/30 10:45
 * @Version 1.0
 **/
public class SimpleFunction implements Computable<String,Integer>{
    @Override
    public Integer compute(String arg) throws Exception {
        return new Integer(arg);
    }
}
