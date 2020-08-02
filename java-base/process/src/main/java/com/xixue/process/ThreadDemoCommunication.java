package com.xixue.process;

import org.apache.log4j.Logger;

/**
 * 线程之间通信
 * 
 * @author houdo
 *
 */
public class ThreadDemoCommunication {
	Logger logger = Logger.getLogger(ThreadDemoCommunication.class);

	public static void main(String[] args) {

		Resource resource = new Resource();
		TreadProduce produce = new TreadProduce(resource);
		ThreadConsumer consumer = new ThreadConsumer(resource);
		produce.start();
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		consumer.start();
		

	}

}

/**
 * 操作对象
 * 
 * @author houdo
 *
 */
class Resource {
	private String name;
	private String sex;
	//线程通讯标识
	public boolean flag = false;
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	@Override
	public String toString() {
		return "Resource [name=" + name + ", sex=" + sex + "]";
	}

}

/**
 * 生产者
 * 
 * @author houdo
 *
 */
class TreadProduce extends Thread {

	Logger logger = Logger.getLogger(TreadProduce.class);
	Resource resource;
	public TreadProduce(Resource resource) {
		this.resource = resource;
	}

	@Override
	public void run() {
		int num = 0;
		while (true) {
			synchronized (resource) {
				if (resource.flag) {
					try {
					   // 当前线程变为等待，但是可以释放锁
						resource.wait();
					} catch (Exception e) {

					}
				}
				if (num == 0) {
					resource.setName("小红");
					resource.setSex("女");
				} else {
					resource.setName("小明");
					resource.setSex("男");
				}
				num = (num + 1) % 2;
				resource.flag = true;
				// 唤醒当前线程
				resource.notify();
			}
		}
	}
}

/**
 * 消费者
 * 
 * @author houdo
 *
 */
class ThreadConsumer extends Thread{
	Logger logger = Logger.getLogger(ThreadConsumer.class);

	Resource resource;

	public ThreadConsumer(Resource resource) {
		this.resource = resource;
	}

	@Override
	public void run() {
		while (true) {
			synchronized (resource) {
				if (!resource.flag) {
					try {
						resource.wait();
					} catch (Exception e) {
					}
				}
				logger.info(resource.toString());
				resource.flag = false;
				resource.notify();
			}

		}
	}
}