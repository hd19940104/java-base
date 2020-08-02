package com.zixue.web.fileter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 解决跨域(ajax请求不是本域名发起的请求)
 * 1、前端使用jsonp（支持get：生成一个get请求发送到服务器）
 * 2、响应头设置运行跨域
 * 3、搭建网管系统
 * 4、使用后台服务转发（占宽带）
 * @author houdo
 *
 */
public class CrossDomainFilter  implements javax.servlet.Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		javax.servlet.http.HttpServletRequest req = (HttpServletRequest) request;
		javax.servlet.http.HttpServletResponse resp=  (HttpServletResponse) response;
		
		//设置请求头
		resp.setHeader("Access-Control-Allow-Origin","*");
		chain.doFilter(req, resp);
		
	
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	
}
