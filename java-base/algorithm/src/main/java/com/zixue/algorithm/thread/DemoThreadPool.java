package com.zixue.algorithm.thread;

import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.*;

/**
 * 线程池
 */
public class DemoThreadPool {
    public static void main(String[] args) {
        Executors.newCachedThreadPool();


        new ThreadPoolExecutor(1, 1, 1, TimeUnit.HOURS, new SynchronousQueue<Runnable>());




    }
}
