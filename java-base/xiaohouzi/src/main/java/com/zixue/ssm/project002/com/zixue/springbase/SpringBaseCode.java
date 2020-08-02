package com.zixue.ssm.project002.com.zixue.springbase;

import org.h2.util.New;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * 
 * @author dell
 * @deprecated Spring 基础
 */
@Component//@Component注解在类上使用表明这个类是个组件类，需要Spring为这个类创建bean。
public class SpringBaseCode {
	
	public static void main(String[] args) {
		
	}
	@Bean//@Bean注解使用在方法上，告诉Spring这个方法将会返回一个Bean对象，需要把返回的对象注册到Spring的应用上下文中。
	public Object springBase() {
		return new Object();
	}
	
	
	
	
}
