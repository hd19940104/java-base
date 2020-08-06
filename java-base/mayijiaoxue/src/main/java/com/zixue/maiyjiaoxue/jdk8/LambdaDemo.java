package com.zixue.maiyjiaoxue.jdk8;

import java.util.ArrayList;
import java.util.List;

/**
 * Lambda 学习demo
 *  函数式接口：
 *      只有一个抽象方法
 *      @FunctionalInterface可有可无
 *
 */
public class LambdaDemo {
    public static void main(String[] args) {
        new LambdaDemo().demo();
    }
    void demo() {
        List list = new ArrayList();
        list.forEach(l -> {

        });
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("this is run0");
            }
        },"thread0").start();
        new Thread(()->{
            System.out.println("this is run1");
        }).start();
    }
}
