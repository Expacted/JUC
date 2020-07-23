package com.coderman.jmm;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * 可见性问题
 * @Author zhangyukang
 * @Date 2020/7/2 15:51
 * @Version 1.0
 **/
public class Visibility2Test {

    private static volatile List<String>   strings=new ArrayList<>();

    public static void main(String[] args)  {

        new Thread(()->{
            while (true){
               if(strings.contains("hello")){
                   break;
               }
            }
            System.out.println("stop");
        },"线程1").start();

        //线程2用来修改flag的值
        new Thread(()->{
            try {
                //此处睡眠是为了保证,线程一已经将 strings 读取到自己的工作内存
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            strings.add("hello");
        },"线程2").start();

    }
}
