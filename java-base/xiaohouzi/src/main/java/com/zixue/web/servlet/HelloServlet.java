package com.zixue.web.servlet;

import javax.servlet.http.HttpServlet;
/**
 * init ()
 * service ()   get put put
 * destroy ()
 * @author houdo
 *	证明servlet是不是线程安全
 *	1、单列线程安全
 *	2、多列线程不安全
 *	3、servlet是通过无参构造函数反射进行创建单列对象
 *	4、
 */
/**
 * 作用域：
 * 	request：只能在转发有效
 * 	context：上下文 保存在服务器端
 * 	会话管理：登录、购物车移动端app会话管理
 * 		cookie：
 * 		session：
 * @author houdo
 *
 */
public class HelloServlet extends HttpServlet {
	@Override
	protected void doGet(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp)
			throws javax.servlet.ServletException, java.io.IOException {
		// TODO Auto-generated method stub
		resp.setCharacterEncoding("utf-8");//内容编码 ，防止出现中文乱码
		resp.setContentType("text/html;charset=utf-8");//向浏览器输出内容
		resp.getWriter().write("hello,this is helloServlet");
		String contextPath = this.getServletContext().getContextPath();
		/**
		 * 重定向： 作用域的值失效、地址改变
		 * 转发 ：
		 */
			req.setAttribute("username", "qwe");
//			resp.sendRedirect(contextPath+"/hello2"); //重定向
			/**
			 * 重定向原理
			 */
			resp.setStatus(302);
			resp.setHeader("Location", "hello2");
			//req.getRequestDispatcher("/hello2").forward(req, resp);//转发
	}
	/**
	 * 初始化操作
	 */
	@Override
	public void init() throws javax.servlet.ServletException {
		// TODO Auto-generated method stub
		System.out.println("开始init");
	}
	/**
	 * 销毁操作
	 */
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		System.out.println("结束destroy");
	}
	/**
	 * post请求
	 */
	@Override
	protected void doPost(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp)
			throws javax.servlet.ServletException, java.io.IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
	}
	/**
	 * 构造函数
	 */
	public HelloServlet(){
		System.out.println("构造函数执行一次，单列");
	}
}
