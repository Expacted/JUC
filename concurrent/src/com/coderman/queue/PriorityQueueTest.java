package com.coderman.queue;

import org.junit.Test;

import java.util.PriorityQueue;

/**
 * 优先级队列
 * @Author zhangyukang
 * @Date 2020/8/6 09:38
 * @Version 1.0
 **/
public class PriorityQueueTest {

    private static PriorityQueue<String> priorityQueue=new PriorityQueue<>();

    public static void main(String[] args) {
        priorityQueue.add("y");
        priorityQueue.add("a");
        priorityQueue.add("c");
        priorityQueue.add("e");
        priorityQueue.add("o");
        priorityQueue.add("z");

        int size = priorityQueue.size();
        for (int i = 0; i < size; i++) {
            String el = priorityQueue.poll();
            System.out.println(el);
        }
    }

    @Test
    public void test(){
        PriorityQueue<Student> priorityQueue=new PriorityQueue<>();
        priorityQueue.add(new Student(1));
        priorityQueue.add(new Student(13));
        priorityQueue.add(new Student(55));
        priorityQueue.add(new Student(11));
        priorityQueue.add(new Student(5));
        priorityQueue.add(new Student(8));
        int size = priorityQueue.size();
        for (int i = 0; i < size; i++) {
            System.out.println(  priorityQueue.poll());
        }
    }
}

class Student implements Comparable<Student>{

    private Integer num;

    @Override
    public int compareTo(Student o) {
        return num.compareTo(o.num);
    }

    public Student(Integer num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return "Student{" +
                "num=" + num +
                '}';
    }
}
