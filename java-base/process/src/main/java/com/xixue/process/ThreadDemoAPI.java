package com.xixue.process;

import org.apache.log4j.Logger;

/**
 * 常用线程API
 * @author houdo
 *
 */
public class ThreadDemoAPI {
	private static Logger logger = Logger.getLogger(ThreadDemoAPI.class);
	public static void main(String[] args) {
		
		DemoThread demoThread = new DemoThread();
		demoThread.start();
		
		
	}
	
}

class DemoThread extends Thread{
	private static Logger logger = Logger.getLogger(DemoThread.class);
	@Override
	public void run() {
		for (int i = 0; i < 20; i++) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				logger.error(e);
			}
			logger.info(getName()+"--------"+currentThread().getId());
		}
	}
}
