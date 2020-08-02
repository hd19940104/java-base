package com.xixue.process;

import org.apache.log4j.Logger;
/**
 * 线程同步
 * @author houdo
 *
 */
public class ThreadDemoSync {
	public static void main(String[] args) {

		ProveSynchronizedByThis threadTrain = new ProveSynchronizedByThis();
		Thread thread = new Thread(threadTrain, "t1");
		Thread thread2 = new Thread(threadTrain, "t2");
		thread.start();
		
		try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
			
		}
		threadTrain.flag = false;
		thread2.start();
	}
}

/**
 * 证明同步方法相当于同步代码块this
 * 
 * @author houdo
 *
 */
class ProveSynchronizedByThis implements Runnable {
	Logger logger = Logger.getLogger(ProveSynchronizedByThis.class);
	private int count = 100;
	private Object obj = new Object();
	public boolean flag = true;
	public synchronized void show() {
		if (count > 0) {
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				logger.error(e);
			}
			logger.info(Thread.currentThread().getName() + "[" + (100 - count + 1) + "]");
			count--;
		}

	}

	@Override
	public void run() {
		if (flag) {
			while (count > 0) {
				synchronized (this) {
					if (count > 0) {
						try {
							Thread.sleep(40);
						} catch (InterruptedException e) {
							logger.error(e);
						}
						logger.info(Thread.currentThread().getName() + "[" + (100 - count + 1) + "]");
						count--;
					}
				}
			}

		} else {
			while (count > 0) {
				show();
			}
		}

	}
}

/**
 * 同步代码块synchronized
 * 
 * @author houdo
 *
 */
class ThreadTrain implements Runnable {
	Logger logger = Logger.getLogger(ThreadTrain.class);
	private int count = 100;
	private Object obj = new Object();

	@Override
	public void run() {

		while (count > 0) {

			synchronized (obj) {
				if (count > 0) {
					try {
						Thread.sleep(40);
					} catch (InterruptedException e) {
						logger.error(e);
					}
					logger.info(Thread.currentThread().getName() + "[" + (100 - count + 1) + "]");
					count--;
				}

			}
		}

	}
}

/**
 * 同步方法synchronized
 * 
 * @author houdo
 *
 */
class ThreadTrain2 implements Runnable {
	Logger logger = Logger.getLogger(ThreadTrain2.class);
	private int count = 100;
	private Object obj = new Object();

	@Override
	public void run() {

		while (count > 0) {
			show();
		}

	}

	public synchronized void show() {

		if (count > 0) {
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				logger.error(e);
			}
			logger.info(Thread.currentThread().getName() + "[" + (100 - count + 1) + "]");
			count--;
		}

	}
}
