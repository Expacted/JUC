package com.coderman.cache.compute;

/**
 * 计算的接口
 * @Author zhangyukang
 * @Date 2020/7/30 10:32
 * @Version 1.0
 **/
public interface Computable<A,V> {
    V compute(A arg) throws Exception;
}
