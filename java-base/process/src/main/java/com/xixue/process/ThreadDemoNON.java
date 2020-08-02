package com.xixue.process;
import com.xixue.BaseFunction;

/**
 * 匿名内部类
 * @author houdo
 *
 */
public class ThreadDemoNON  {
	private static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(ThreadDemoNON.class);
	public static void main(String[] args) {
		logger.info("创建线程开始");
		new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < 200; i++) {
					if (i==199) {
					 System.out.println(10/0);
					}
					logger.info("run"+i);
				}
				
			}
		}).start();
		
		logger.info("创建线程结束");
		for (int i = 0; i < 200; i++) {
			
			logger.info("run"+i);
		}
	}
}



