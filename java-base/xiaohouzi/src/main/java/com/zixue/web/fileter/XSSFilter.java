package com.zixue.web.fileter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;

public class XSSFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		
		XssAndSqlHttpServletRequestWrapper wrapper = new XssAndSqlHttpServletRequestWrapper(req);
		
		chain.doFilter(req, resp);
		
		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}

/**
 * XSS攻击
 * 将请求参数转成 html标签
 *
 * @author houdo
 *
 */
 class XssAndSqlHttpServletRequestWrapper extends  HttpServletRequestWrapper {
	private HttpServletRequest request;

	public XssAndSqlHttpServletRequestWrapper(HttpServletRequest request) {
		super(request);
		this.request = request;
	}

	/**
	 * 将request 中的 value值 重写一下， 将一些 脚本参数 非法参数准换成html元素执行。
	 */
	@Override
	public String getParameter(String name) {
		String value = this.request.getParameter(name);
		if (!StringUtils.isEmpty(value)) {
			System.out.println("转换前value:" + value);
			value = StringEscapeUtils.escapeHtml4(value);
			System.out.println("转换后value:" + value);
		}
		return value;

	}

}

