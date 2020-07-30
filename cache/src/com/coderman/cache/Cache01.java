package com.coderman.cache;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 最简单的缓存
 * @Author zhangyukang
 * @Date 2020/7/30 10:15
 * @Version 1.0
 **/
public class Cache01 {

    private static final Map<String,Integer> cache=new HashMap<>();

    public synchronized Integer compute(String userId){
        Integer result = cache.get(userId);
        if (result == null) {
            try {
                //cache 不存在 , 计算后保存到cache里
                result = doCompute(userId);
                cache.put(userId,result);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    //业务代码
    public Integer doCompute(String userId) throws InterruptedException {
        TimeUnit.SECONDS.sleep(1);
        return new Integer(userId);
    }

    public static void main(String[] args) {
        Cache01 cache01 = new Cache01();
        Integer result = cache01.compute("13");
        System.out.println(result);
        for (int i = 0; i < 10; i++) {
            result = cache01.compute("13");
            System.out.print(result+"\t");
        }
    }
}
