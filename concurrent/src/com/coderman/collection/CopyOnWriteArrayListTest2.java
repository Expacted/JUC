package com.coderman.collection;

import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * CopyOnWriteArrayList 数据过期的情况
 * 时候读多写少的情况.
 * @Author zhangyukang
 * @Date 2020/7/24 15:16
 * @Version 1.0
 **/
public class CopyOnWriteArrayListTest2 {
    public static void main(String[] args) {
        List<Integer> list= new CopyOnWriteArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        Iterator<Integer> iterator1 = list.iterator();
        list.add(6);
        ListIterator<Integer> iterator2 = list.listIterator();
        System.out.println(list);
        System.out.println("---------第一次遍历-----------");

        while (iterator1.hasNext()){
            Integer next = iterator1.next();
            System.out.print(next+"\t");
        }
        System.out.println();

        System.out.println("---------第二次遍历-----------");

        while (iterator2.hasNext()){
            Integer next = iterator2.next();
            System.out.print(next+"\t");
        }
    }
}
