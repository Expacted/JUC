package com.coderman.juc.exception;

/**
 * @Author zhangyukang
 * @Date 2020/5/12 19:32
 * @Version 1.0
 **/
public class GirlfriendNotFoundException extends RuntimeException{

    public GirlfriendNotFoundException(String message) {
        super(message);
    }
}
