package com.zixue.web.fileter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 *  过滤器  （防盗链）
 *  获取请求体中的 referer 信息（访问来源）
 * @author houdo
 *
 */
public class RefererFilter implements Filter{

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		//每次请求来源
		String referer = req.getHeader("referer");
		//获取请求地址
		String serverName = req.getServerName();
		if (referer == null || !referer.contains(serverName)) {
			req.getRequestDispatcher("static/img/err/error.png").forward(req,resp);
			return;
		}
		//放行
		chain.doFilter(req, resp);
	
	}

	@Override
	public void destroy() {
		
	}

}
