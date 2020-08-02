package com.zixue.web.session;

import javax.servlet.http.HttpSession;
/**
 *  session 会话管理
 * @author houdo
 *
 */
@javax.servlet.annotation.WebServlet("/session1")
public class SessionServlet extends javax.servlet.http.HttpServlet{
	@Override
	protected void doGet(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp)
			throws javax.servlet.ServletException, java.io.IOException {
		HttpSession session = req.getSession();
		if (session!=null) {
			session.setAttribute("username", "123");
		}
		String attribute = (String) session.getAttribute("username");
		System.out.println("session:"+attribute);
		
	}
}
