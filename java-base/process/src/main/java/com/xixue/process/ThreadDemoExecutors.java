package com.xixue.process;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.junit.Test;




/**
 * 并发包 newCachedThreadPool newFixedThreadPool newScheduleThreadPool
 * newSingleThreadExecutors
 * 
 * @author houdo
 *
 */
public class ThreadDemoExecutors {
	@Test
	private void test01() {
		ExecutorService pool = Executors.newCachedThreadPool(); //具有缓存线程
		for (int i = 0; i < 10; i++) {
			int index = i;
			pool.execute(new Runnable() {
				@Override
				public void run() {
					try {
						/*Thread.sleep(400);*/
					} catch (Exception e) {
						// TODO: handle exception
					}
					System.out.println(Thread.currentThread() + "----" + index);

					/*if (index == 9) {
						newCachedThreadPool.shutdown();
					}*/
				}
			});

		}
	}
	@Test
	private void test02() {
		ExecutorService pool = Executors.newFixedThreadPool(2); //每次执行固定的线程个数
		for (int i = 0; i < 10; i++) {
			int index = i;
			pool.execute(new Runnable() {
				@Override
				public void run() {
					try {
						Thread.sleep(400);
					} catch (Exception e) {
						// TODO: handle exception
					}
					System.out.println(Thread.currentThread() + "----" + index);

					/*if (index == 9) {
						newCachedThreadPool.shutdown();
					}*/
				}
			});

		}
	}
	@Test
	private void test03() {
		ScheduledExecutorService pool = Executors.newScheduledThreadPool(5); //定时的线程池
		pool.schedule(new Runnable() {
		public void run() {
			System.out.println("三秒之后执行");
			
		}}, 3, TimeUnit.SECONDS);
		
		
	}
	public static void main(String[] args) {
//		ExecutorService pool = Executors.newCachedThreadPool(); //具有缓存线程
//		ExecutorService pool = Executors.newFixedThreadPool(2); //每次执行固定的线程个数
//		ScheduledExecutorService pool = Executors.newScheduledThreadPool(5); //定时的线程池
		ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor(); //单线程池
		
		for (int i = 0; i < 10; i++) {
			int index = i;
			singleThreadExecutor.execute(new Runnable() {
				@Override
				public void run() {
					try {
						Thread.sleep(400);
					} catch (Exception e) {
						// TODO: handle exception
					}
					System.out.println(Thread.currentThread() + "----" + index);

					/*if (index == 9) {
						newCachedThreadPool.shutdown();
					}*/
				}
			});

		}
	}

}
