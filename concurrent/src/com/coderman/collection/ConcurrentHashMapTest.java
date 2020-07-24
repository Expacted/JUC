package com.coderman.collection;

import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * ConcurrentHashMap 不存在数据过期问题
 * @Author zhangyukang
 * @Date 2020/7/24 10:57
 * @Version 1.0
 **/
public class ConcurrentHashMapTest {
    public static void main(String[] args) {
        Map<Integer,String> map=new ConcurrentHashMap<>();
        map.put(1,"1");
        map.put(2,"2");
        map.put(3,"3");
        map.put(4,"4");
        map.put(5,"5");
        Iterator<Map.Entry<Integer, String>> iterator = map.entrySet().iterator();


        map.put(6,"6");

        Iterator<Map.Entry<Integer, String>> iterator2 = map.entrySet().iterator();

        while (iterator.hasNext()){
            Map.Entry<Integer, String> next = iterator.next();
            System.out.println(next.getKey()+"--->"+next.getValue());
        }

        System.out.println("--------------------------------------");

        while (iterator2.hasNext()){
            Map.Entry<Integer, String> next = iterator2.next();
            System.out.println(next.getKey()+"--->"+next.getValue());
        }


    }
}
