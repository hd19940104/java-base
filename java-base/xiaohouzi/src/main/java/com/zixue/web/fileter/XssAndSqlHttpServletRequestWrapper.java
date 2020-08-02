package com.zixue.web.fileter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
/**
 * XSS攻击
 * 将请求参数转成 html标签
 *
 * @author houdo
 *
 */
//public class XssAndSqlHttpServletRequestWrapper extends  HttpServletRequestWrapper {
//	private HttpServletRequest request;
//
//	public XssAndSqlHttpServletRequestWrapper(HttpServletRequest request) {
//		super(request);
//		this.request = request;
//	}
//
//	/**
//	 * 将request 中的 value值 重写一下， 将一些 脚本参数 非法参数准换成html元素执行。
//	 */
//	@Override
//	public String getParameter(String name) {
//		String value = this.request.getParameter(name);
//		if (!StringUtils.isEmpty(value)) {
//			System.out.println("转换前value:" + value);
//			value = StringEscapeUtils.escapeHtml4(value);
//			System.out.println("转换后value:" + value);
//		}
//		return value;
//
//	}
//
//}
