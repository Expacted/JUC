package com.coderman.threadsafe;

import org.omg.CORBA.OBJ_ADAPTER;

import java.util.HashMap;
import java.util.Map;

/**
 * 对象逸出后线程不安全
 * @Author zhangyukang
 * @Date 2020/6/30 11:53
 * @Version 1.0
 **/
public class ObjectEscape {
    private static Map<String,String> resource;

    static {
        resource=new HashMap<>();
        resource.put("username","zhangyukang");
        resource.put("password","123456");
    }

    public static Map<String, String> getResource() {
//        return resource;
        return new HashMap<>(resource);//解决逸出方式，使用对象的副本
    }
}
class Tester{
    public static void main(String[] args) {
        Map<String, String> resource = ObjectEscape.getResource();
        System.out.println(resource);

        resource.put("username","hello");//这里模仿被其他线程修改

        new Thread(()->{
            Map<String, String> res = ObjectEscape.getResource();
            System.out.println(res);
        }).start();
    }
}
