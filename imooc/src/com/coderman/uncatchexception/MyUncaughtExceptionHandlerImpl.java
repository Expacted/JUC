package com.coderman.uncatchexception;

/**
 * 自定义异常全局处理
 * @Author zhangyukang
 * @Date 2020/6/30 10:32
 * @Version 1.0
 **/
public class MyUncaughtExceptionHandlerImpl implements Thread.UncaughtExceptionHandler {

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        System.out.println("全局异常捕捉到了异常 ，线程："+t.getName()+" 异常信息："+e.getMessage());
    }
}
