package com.zixue.web.session;

public class TokenUtils {
	static public String getToken() {
		return java.util.UUID.randomUUID().toString(); 
	}
}
