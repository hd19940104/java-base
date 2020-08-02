package com.xixue.process;

/**
 * ThreadLocal
 * 
 * @author houdo
 *
 */
public class ThreadDemoThreadLocal {
	public static ThreadLocal<Integer> threadLocal = new ThreadLocal<Integer>() {
		@Override
		protected java.lang.Integer initialValue() {
			// TODO Auto-generated method stub
			return 0;
		}
	};
	public static void main(String[] args) {
		
		Thread[] threads = new Thread[5];
		
		for (Thread thread : threads) {
			thread =new Thread(new Runnable() {
				@Override
				public void run() {
					Integer integer = threadLocal.get();
					integer +=5;
					System.out.println(Thread.currentThread().getName() + "---" + integer);
				}
			});
			thread.start();
		}
		
	}

}

class DemoThreadLocal {


}
