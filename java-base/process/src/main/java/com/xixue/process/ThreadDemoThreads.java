package com.xixue.process;

import org.apache.log4j.Logger;

/**
 * 继承Thread
 * @author houdo
 *	创建线程
 */
public class ThreadDemoThreads {
	private static Logger logger = Logger.getLogger(ThreadDemoThreads.class);
	private static String flag="";
	public static void main(String[] args) {
		logger.info("开始创建线程");
		CreateThread createThread = new CreateThread();
		createThread.setName("slave");
		createThread.start();
//		createThread.run();
		logger.info("线程已经开始执行");

		for (int i = 0; i < 200; i++) {
			logger.info("main"+i);
		}
	
		
	}
	
}


class CreateThread extends Thread{
	private  Logger logger = Logger.getLogger(CreateThread.class);
	@Override
	public void run() {
		for (int i = 0; i < 200; i++) {
			if (i==199) {
			 System.out.println(10/0);
			}
			logger.info("run"+i);
		}
	}
}