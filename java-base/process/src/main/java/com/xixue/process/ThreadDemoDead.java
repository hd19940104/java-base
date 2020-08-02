package com.xixue.process;

import org.apache.log4j.Logger;
/**
 * 线程死锁
 * @author houdo
 *
 */
public class ThreadDemoDead {
	public static void main(String[] args) throws InterruptedException {
		DeadThread threadTrain = new DeadThread();
		Thread thread = new Thread(threadTrain, "t1");
		Thread thread2 = new Thread(threadTrain, "t2");
		thread.start();
		Thread.sleep(10000);
		threadTrain.flag = false;
		thread2.start();
	}

}

class DeadThread implements Runnable {
	Logger logger = Logger.getLogger(DeadThread.class);
	private int count = 100;
	Object obj1 = new Object();
	Object obj2 = new Object();
	public boolean flag = true;

	@Override
	public void run() {
		if (flag) {
			while (count > 0) {
				synchronized (obj1) {
					show();
				}
			}

		} else {
			while (count > 0) {
				synchronized (obj2) {
					show2();
				}
			}
		}

	}
	public  void show2() {
		synchronized (obj1) {
			logger.info("------------------");
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
	public  void show() {
		synchronized (obj2) {
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
}