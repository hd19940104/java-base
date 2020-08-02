package com.zixue.util;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import javax.servlet.http.HttpServletRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
public class WebLogAspect {
	private Logger logger = LoggerFactory.getLogger(getClass());
	long currentTimeMillis =0 ;
	Date date =null ;
	SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:dd:ss");
	/**
	 * 
	 * @Title  webLog 
	 * @Description  TODO void
	 */
	@Pointcut("execution(public * com.zixue..*.*(..))")
	public void webLog() {
	}
	/**
	 * 
	 * @Title  time 
	 * @Description  //匹配sample包及其子包下所有类的方法
	 */
	@Pointcut("within(com.zixue.controller..*)")
	public void time() {
	}
	/**
	 * 
	 * @Title  doBefore_time 
	 * @Description  TODO
	 * @param joinPoint
	 * @throws Throwable void
	 */
	@Before("time()")
	public void doBefore_time(JoinPoint joinPoint) throws Throwable {
		currentTimeMillis = System.currentTimeMillis();
	}
	/**
	 * 
	 * @Title  doAfterReturning_time 
	 * @Description  TODO
	 * @param ret
	 * @throws Throwable void
	 */
	@AfterReturning(returning = "ret", pointcut = "time()")
	public void doAfterReturning_time(Object ret) throws Throwable {
		this.logger.info("=================耗时 : 【" + (System.currentTimeMillis()-currentTimeMillis) +"(ms)】");
	}

}

