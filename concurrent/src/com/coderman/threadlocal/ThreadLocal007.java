package com.coderman.threadlocal;

/**
 * 使用ThreadLocal解决参数传递问题
 * @Author zhangyukang
 * @Date 2020/7/14 11:51
 * @Version 1.0
 **/
public class ThreadLocal007 {
    public static void main(String[] args) {
        User user = new User("zhangyukang");
        UserContextHolder02.set(user);//当前线程的设置线程局部变量
        Service01.doService();
    }
}


class Service01{
    public static void doService(){
        System.out.println("service01 get user="+UserContextHolder02.get());
        Service02.doService();
    }
}

class Service02{
    public static void doService(){
        User java = new User("java");
        UserContextHolder02.remove();//移除
        UserContextHolder02.set(java);//重新设置
        System.out.println("service02 get user="+UserContextHolder02.get());
        Service03.doService();
    }
}

class Service03{
    public static void doService(){
        System.out.println("service03 get user="+UserContextHolder02.get());
    }
}

class UserContextHolder02{
    private static ThreadLocal<User> threadLocal=new ThreadLocal<>();

    public static void set(User user){
        threadLocal.set(user);
    }

    public static User get(){
        User user = threadLocal.get();
        return user;
    }

    public static void remove(){
        threadLocal.remove();
    }
}
