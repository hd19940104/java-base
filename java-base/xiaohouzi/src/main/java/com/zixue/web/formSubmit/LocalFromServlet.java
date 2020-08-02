
package com.zixue.web.formSubmit;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.zixue.web.session.TokenUtils;


/**
 * 功能描述:(跳转到from.jsp)
 * @author dell
 *
 */
@WebServlet("/login")
public class LocalFromServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		   // 生成token
		String tokenValue=TokenUtils.getToken();
		HttpSession session = req.getSession();
		session.setAttribute("token", tokenValue);
		System.out.println(session.getAttribute("token"));
		req.getRequestDispatcher("from.jsp").forward(req, resp);
	}
	
}
