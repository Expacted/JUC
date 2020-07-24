package com.coderman.collection;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 在使用迭代器遍历的时候,不能修改集合内的元素
 * @Author zhangyukang
 * @Date 2020/7/23 18:52
 * @Version 1.0
 **/
public class HashMapTest2 {
    public static void main(String[] args) {
        Map<Integer, String> map = new ConcurrentHashMap<>();
        map.put(1, "one");
        map.put(2, "two");
        map.put(3, "three");

        /** 迭代方式1
         Set<Integer> keySet = map.keySet();
         System.out.println(keySet);
         for (Integer key : keySet) {
         String value = map.get(key);
         System.out.print(value+"\t");
         }*/
        for(Map.Entry<Integer,String> entry: map.entrySet()){
            System.out.println(entry.getKey()+"----->"+entry.getValue());
        }
    }
}
