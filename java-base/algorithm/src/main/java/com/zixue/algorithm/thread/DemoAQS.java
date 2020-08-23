package com.zixue.algorithm.thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * AQS
 *  是指 AbstractQueuedSynchronizer  是一个抽象类
 */
public class DemoAQS extends AbstractQueuedSynchronizer {

    public static void main(String[] args) {

        CountDownLatch countDownLatch=new CountDownLatch(10);
        Semaphore semaphore=new Semaphore(10);
        CyclicBarrier cyclicBarrier=new CyclicBarrier(10);

    }


}
