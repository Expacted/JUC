package com.coderman.juc.collection;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * 容器类线程不安全
 * @Author zhangyukang
 * @Date 2020/5/12 16:34
 * @Version 1.0
 **/
public class NotSafeCollection {

    public static void main(String[] args) {
        ArrayList<String> arrayList=new ArrayList<>();
        arrayList.add("zhang");
        arrayList.add("yu");
        arrayList.add("kang");
        Iterator<String> iterator = arrayList.iterator();
//        //抛出异常:java.util.ConcurrentModificationException
        while (iterator.hasNext()){
            if(iterator.next().equals("kang")){
                arrayList.add("is a handsome boy !");
            }
        }
        //增强for循环底层使用的是迭代器
//        for (String s : arrayList) {
//            if(s.equals("kang")){
//                arrayList.add("is a handsome boy !");
//            }
//        }
//        for(int i=0;i<arrayList.size();i++){
//            if(arrayList.get(i).equals("kang")){
//                arrayList.add("is a handsome boy !");
//            }
//        }

        System.out.println(arrayList);
    }

}
