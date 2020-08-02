package com.xixue.process;

import org.apache.log4j.Logger;

/**
 * java内存模型  JMM   共享全局变量  + 线程本地变量
 * @author houdo
 *
 */
public class ThreadDemoJMM {
	static Logger logger = Logger.getLogger(ThreadDemoJMM.class);
	
	
	public static void main(String[] args) throws InterruptedException {
		DemoJMM demoJMM = new DemoJMM();
		demoJMM.start();
		Thread.sleep(100);
		demoJMM.getRun(false);
	
	}
	
}


class DemoJMM extends Thread{
	 public  volatile  boolean flag = true;
	@Override
	public void run() {
		System.out.println("子线程开始执行");
		while (flag) {
			
		}
		System.out.println("子线程结束");
	}
	public void getRun(boolean flag){
		this.flag = flag;
	}
	
}


