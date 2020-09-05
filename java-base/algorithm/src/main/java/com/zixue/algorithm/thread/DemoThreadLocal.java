package com.zixue.algorithm.thread;

import java.util.Vector;

public class DemoThreadLocal {
    static  ThreadLocal<Integer> threadLocal =new ThreadLocal<Integer>();

    public static void main(String[] args) {
        Vector vector = new Vector();
            int num=0;
        for (int i=0 ; i<10000000 ; i++){
            int finalI = i;
            num++;
            int finalNum = num;
            new Thread(new Runnable() {
                @Override
                public void run() {

                    threadLocal.set(10000000);
                    System.out.println(finalI);
                    try {
                        Thread.sleep(100000);
                    } catch (InterruptedException e) {
                        System.out.println(finalNum);
                        e.printStackTrace();

                    }
                }
            }).start();

        }



    }
}
