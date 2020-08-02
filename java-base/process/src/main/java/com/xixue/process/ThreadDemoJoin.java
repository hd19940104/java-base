package com.xixue.process;

import org.apache.log4j.Logger;

/**
 * 线程join方法
 * @author houdo
 *
 */
public class ThreadDemoJoin {
	static Logger logger  = Logger.getLogger(ThreadDemoJoin.class);
	public static void main(String[] args) throws InterruptedException {
		
		Thread t1 =  new Thread(new Runnable() {
			@Override
			public void run() {
				for(int i =0 ; i < 100 ; i++) {
					logger.info(Thread.currentThread().getName()+"--"+i);
				}
				
			}
		},"t1");
		Thread t2 =  new Thread(new Runnable() {
			@Override
			public void run() {
				for(int i =0 ; i < 100 ; i++) {
					logger.info(Thread.currentThread().getName()+"--"+i);
				}
				
			}
		},"t2");
		t1.start();
		t1.join();
		t2.start();
		for(int i =0 ; i < 100 ; i++) {
			logger.info(Thread.currentThread().getName()+"--"+i);
		}
		
		
	}
}


