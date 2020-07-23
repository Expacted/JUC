package com.coderman.finaltest;

/**
 * final修饰变量值不可改变,修饰方法不能重写,修饰类无法被继承
 * @Author zhangyukang
 * @Date 2020/7/21 21:39
 * @Version 1.0
 **/
public class FinalDemo {
    public static void main(String[] args) {
        final int a=20;
//        a=89;//无法修改
        final Person person=new Person("zhang");
//        person=new Person("li");//无法修改
        person.setName("test");//可以修改引用指向对象的值
    }
}
class Person{
    private String name;

    public Person(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
