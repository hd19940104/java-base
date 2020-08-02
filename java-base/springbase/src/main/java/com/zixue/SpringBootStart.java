package com.zixue;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;

import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/**
 * 
 * @ClassName  SpringBootStart 
 * @Description TODO
 * @author 一只会飞的小猴子
 * @date  2019年8月7日 下午5:40:56 
 *
 */
//@MapperScan("com.zixue.project3.dao")
@SpringBootApplication
public class SpringBootStart {
	private static Logger logger = Logger.getLogger(SpringBootStart.class);
	/**
	 * 
	 * @Title  main 
	 * @Description  TODO
	 * @param args void
	 */
	public static void main(String[] args) {
		long currentTimeMillis = System.currentTimeMillis();
		logger.info("启动spring-boot 程序...");
		SpringApplication.run(SpringBootStart.class, args);
		logger.info("启动成功,耗时:"+(System.currentTimeMillis()-currentTimeMillis)+"(ms)");
		
	}

}
