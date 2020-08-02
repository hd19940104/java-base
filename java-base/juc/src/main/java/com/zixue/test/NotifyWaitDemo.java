package com.zixue.test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class NotifyWaitDemo {

	private int number = 0;
	private Lock lock = new ReentrantLock();
	private Condition condition = lock.newCondition();

	/**
	 * 添加
	 * 
	 * @throws InterruptedException
	 */
//	public synchronized void increment() throws InterruptedException {
//
//		// 1,判断
//		while (number != 0) {
//			this.wait();
//		}
//
//		// 2干活
//		++number;
//		System.out.println(Thread.currentThread().getName() + "\t" + number);
//
//		// 通知
//		this.notifyAll();
//
//	}

	public  void increment1() throws InterruptedException {

		lock.lock();
		try {
			// 1,判断
			while (number != 0) {
				condition.await();
			}

			// 2干活
			++number;
			System.out.println(Thread.currentThread().getName() + "\t" + number);

			// 通知
			condition.signalAll();
		} finally {
			lock.unlock();
		}

	}

	/**
	 * 减少
	 * 
	 * @throws InterruptedException
	 */
//	public synchronized void decrement() throws InterruptedException {
//
//		// 1,判断
//		while (number == 0) {
//			this.wait();
//		}
//
//		// 2干活
//		--number;
//		System.out.println(Thread.currentThread().getName() + "\t" + number);
//
//		// 通知
//		this.notifyAll();
//
//	}

	public void decrement1() throws InterruptedException {
		lock.lock();
		try {
			// 1,判断
			while (number == 0) {
				condition.await();
			}

			// 2干活
			--number;
			java.util.concurrent.TimeUnit.SECONDS.sleep(11);
			System.out.println(Thread.currentThread().getName() + "\t" + number);

			// 通知
			condition.signalAll();
		} finally {
			lock.unlock();
		}

	}

}
