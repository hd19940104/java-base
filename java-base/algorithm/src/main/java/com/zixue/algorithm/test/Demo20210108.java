package com.zixue.algorithm.test;

import java.util.Stack;

public class Demo20210108 {


    private static Stack<Integer> s1;//进行转化顺序
    private static Stack<Integer> s2;//等同于queue

    public static void push(int n){

        if (s1==null){
            s1=new Stack();
        }
        if (s2==null){
            s2=new Stack();
        }
        s1.push(n);


    }

    public static int pop(){
        while (!s1.isEmpty()){
            s2.push(s1.pop());
        }
        return  s2.pop();

    }


    public static void main(String[] args) {
        push(0);
        push(1);
        push(2);

        int i1 = pop();
        int i2 = pop();
        int i3 = pop();
        System.out.print(i1+"-"+i2+"-"+i3);
    }

}
