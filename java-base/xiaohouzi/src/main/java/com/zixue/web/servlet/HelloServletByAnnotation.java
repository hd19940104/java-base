package com.zixue.web.servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
@WebServlet("/hello2")
public class HelloServletByAnnotation extends HttpServlet {
	@Override
	protected void doGet(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp)
			throws javax.servlet.ServletException, java.io.IOException {
		// TODO Auto-generated method stub
		System.out.println("hello2 ");
		resp.getWriter().write("hello,this is HelloServletByAnnotation");
		String attribute = (String) req.getAttribute("username");
		System.out.println(attribute);
	}
}
