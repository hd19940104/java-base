package com.zixue.web.formSubmit;
/**
 * 表单重复提交
 * @author houdo
 *
 */

import javax.servlet.http.HttpServletRequest;

/**
 * 解决思路：
 * 	前端按钮只允许点击一次
 * 	
 * @author houdo
 *
 */
@javax.servlet.annotation.WebServlet("/DoFormServlet")
public class DoFormServlet extends javax.servlet.http.HttpServlet {
	@Override
	protected void doGet(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp)
			throws javax.servlet.ServletException, java.io.IOException {
		resp.setContentType("text/html;charset=utf-8");// 防止浏览器显示乱码
        if(!isBumit(req)){
        	System.out.println("您提交提交了数据..或者token错误!");
        	resp.getWriter().write("您提交提交了数据..或者token错误!");
        	return ;
        }
		String userName = req.getParameter("userName");
		try {
			//Thread.sleep(300);
		} catch (Exception e) {
			// TODO: handle exception
		}
		System.out.println("数据库插入数据...userName:" + userName);
		// 插入数据库...
		resp.getWriter().write("保存成功..");
		req.getSession().removeAttribute("token");
		
	}
	@Override
	protected void doPost(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp)
			throws javax.servlet.ServletException, java.io.IOException {
		doGet(req, resp);
	}
	
	public Boolean isBumit(HttpServletRequest request) {
		String parameterToken = request.getParameter("parameterToken");
		String sessionToken = (String) request.getSession().getAttribute("token");
	    //判断是否提交
		if (sessionToken == null) {
			return false;
		}
		// 判断是否是伪造token
		if(!(parameterToken.equals(sessionToken))){
			return false;
		}
		return true;

	}
}
