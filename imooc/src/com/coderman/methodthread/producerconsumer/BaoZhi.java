package com.coderman.methodthread.producerconsumer;

/**
 * @Author zhangyukang
 * @Date 2020/7/5 16:23
 * @Version 1.0
 **/
public class BaoZhi {
    private int id;
    private String name;

    public BaoZhi(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "BaoZhi{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
