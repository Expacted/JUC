package com.coderman.threadlocal;

/**
 * 使用ThreadLocal解决参数传递问题
 * @Author zhangyukang
 * @Date 2020/7/14 11:51
 * @Version 1.0
 **/
public class ThreadLocal006 {
    public static void main(String[] args) {
        User user = new User("zhangyukang");
        Service1.doService(user);
    }
}
class User{
    private String username;

    public User(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                '}';
    }
}
class Service1{
    public static void doService(User user){
        System.out.println("service1 get user="+user);
        Service2.doService(user);
    }
}
class Service2{
    public static void doService(User user){
        System.out.println("service2 get user="+user);
        Service3.doService(user);
    }
}

class Service3{
    public static void doService(User user){
        System.out.println("service3 get user="+user);
    }
}
