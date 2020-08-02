
package com.zixue.ssm.project001.handler;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@org.springframework.stereotype.Controller
public class UserLoginHandler {
	@RequestMapping("/login001")
	public String login001() {
		System.out.println("login001...");
		return "login001";
	}
	@RequestMapping("/login002")
	public String  login002(@RequestParam String userName,@RequestParam String userPassword) {
		System.out.println("userName:"+userName+"userPassword:"+userPassword);
		return "index";
	}
	@RequestMapping("/login003")
	public String  login003(@RequestBody String userName,@RequestBody String userPassword) {
		System.out.println("userName:"+userName+"userPassword:"+userPassword);
		return "index";
	}
	
	
}
