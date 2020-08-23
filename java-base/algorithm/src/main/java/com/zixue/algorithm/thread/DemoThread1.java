package com.zixue.algorithm.thread;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicInteger;

public class DemoThread1 {
    static AtomicInteger numa=new AtomicInteger(0);
    static  int num=0;
    static  CyclicBarrier cyclicBarrier=new CyclicBarrier(100);
    static CountDownLatch countDownLatch=new CountDownLatch(100000);
    public static void main(String[] args) throws InterruptedException, BrokenBarrierException {


        for (int i=0; i<1000; i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i=0 ;i<1000;i++) {
                        num++;
                        numa.getAndIncrement();
                    }
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    try {

                        cyclicBarrier.await();
//                        countDownLatch.countDown();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();

        }
//        Thread.sleep(1000);
        cyclicBarrier.await();
//        countDownLatch.await();
        System.out.println(num+"--"+numa);
    }
}
