package com.coderman.juc;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author zhangyukang
 * @Date 2020/6/16 11:45
 * @Version 1.0
 **/
public class DebugTest {
    public static void main(String[] args) {
        List<String> strings = new ArrayList<>();
        System.out.println("hello world");
        method1();
        System.out.println("and");
        method2();
        System.out.println(strings);
    }

    private static void method1() {
        System.out.println("method 1");
    }

    private static void method2() {
        System.out.println("method 2");
    }

    /**
     * 方法三
     *
     * @param username 用户名
     * @param password 密码
     */
    private static void method3(String username, String password) {
        System.out.println("username");
    }
}

class UserDAO {
    private String username;
    private String password;
}
