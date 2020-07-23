package com.coderman.atomic;

import java.util.concurrent.atomic.AtomicReference;

/**
 * 引用类型原子变量
 * @Author zhangyukang
 * @Date 2020/7/17 09:19
 * @Version 1.0
 **/
public class AtomicReferenceTest {
    public static void main(String[] args) {
        AtomicReference<Thread> threadAtomicReference = new AtomicReference<>();
        Thread thread = Thread.currentThread();
        threadAtomicReference.compareAndSet(null,thread);
    }
}
