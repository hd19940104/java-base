package com.xixue.process;

import org.apache.log4j.Logger;
/**
 * 实现Runable接口
 * @author houdo
 *
 */
public class ThreadDemoRunable {
	private static Logger logger = Logger.getLogger(ThreadDemoRunable.class);
	public static void main(String[] args) {
		logger.info("创建线程开始");
		CreateRunable createRunable = new CreateRunable();
		Thread thread = new Thread(createRunable);
		thread.start();
		logger.info("创建线程结束");
		for (int i = 0; i < 200; i++) {
			
			logger.info("run"+i);
		}
	}
	
}

class CreateRunable implements Runnable{
	private  Logger logger = Logger.getLogger(CreateRunable.class);
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
