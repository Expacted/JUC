package com.coderman.threadsafe;

import java.util.HashMap;
import java.util.Map;



/**
 * 在构造器中创建线程
 * @Author zhangyukang
 * @Date 2020/6/30 11:40
 * @Version 1.0
 **/
public class CreateThreadInConstructor {

    public static void main(String[] args) throws InterruptedException {
        Demo demo = new Demo();
//        Thread.sleep(5000);
        Map<String, String> resource = demo.getResource();
        String username = resource.get("username");
        System.out.println("username="+username);

    }
}
class Demo{

    private Map<String,String> resource;

    public Demo() {
        new Thread(()->{
            this.resource=new HashMap<>();
            resource.put("password","123456");
            resource.put("username","zhangyukang");
        }).start();
    }

    public Map<String, String> getResource() {
        return resource;
    }

    public void setResource(Map<String, String> resource) {
        this.resource = resource;
    }
}
