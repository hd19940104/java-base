package com.zixue.web.cookie;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *  Cookie 保存获取
 * @author houdo
 *
 */
@WebServlet("/cookie1")
public class CookieServlet extends HttpServlet  {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
	
		resp.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");
		String lastAccessTime = null;
		Cookie[] cookies = req.getCookies();
		for (Cookie cookie : cookies) {
			String name = cookie.getName();
			if (name.equals("lastAccessTime")) {
				lastAccessTime = cookie.getValue();
				break;
			}
		}
		if (lastAccessTime==null) {
			resp.getWriter().print("您是首次访问!");
		} else {
			resp.getWriter().print("你上次访问时间:" + lastAccessTime);
		}
		// 保存访问时间
		// 创建cookie 将当前时间作为cookie保存到浏览器
		String currenttime = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss").format(new Date());
		Cookie cookie = new Cookie("lastAccessTime", currenttime);
		cookie.setMaxAge(60 * 60 * 24);
		// 发送cookie
		resp.addCookie(cookie);

	}
}
