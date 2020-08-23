package com.zixue.algorithm.thread;

import java.util.concurrent.CountDownLatch;

public class ThreadDemo {
    static int i=0;
    static CountDownLatch countDownLatch=new CountDownLatch(10);
    public static void main(String[] args) throws InterruptedException {


        for (int j=0;j<12;j++){
            new Thread(new Runnable() {
                @Override
                public void run() {

                    i++;
                    System.out.println(i);
                    countDownLatch.countDown();
                }
            }).start();
        }

        countDownLatch.await();
     System.out.println(i);
    }
}
