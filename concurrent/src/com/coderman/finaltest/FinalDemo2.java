package com.coderman.finaltest;

/**
 * @Author zhangyukang
 * @Date 2020/7/21 21:42
 * @Version 1.0
 **/
public class FinalDemo2 {
    public static void main(String[] args) {
        String s1="zhangyukang";
        final String s2="yukang";
        String s3="zhang"+s2;//编译器优化,直接相当于字符串zhangyukang
        String s4="zhang";
        String s5=s4+s2;
        String s6= s3.intern();//intern:如果常量池存在zhangyukang,那么返回常量池的引用,如果不存在的话,先在常量池中创建,在返回常量池中字符串地址.
        System.out.println(s1==s3);//因此这里是true,s1,s3都指向字符串常量池
        System.out.println(s5==s1);//这里是false,字符串加一个变量,会在堆区生成一个对象
        System.out.println(s6==s3);
    }
}
