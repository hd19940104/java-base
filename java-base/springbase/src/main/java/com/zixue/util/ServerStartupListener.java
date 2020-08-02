package com.zixue.util;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
/**
 * 服务器启动监听器
 * @author 一只会飞的小猴子
 *
 */
public class ServerStartupListener implements ServletContextListener
{
    
    public void contextInitialized(ServletContextEvent sce)
    {
        //获取上下文路径
        ServletContext application = sce.getServletContext();
        String path = application.getContextPath();
        //保存 到web应用中
        application.setAttribute("APP_PATH", path);
        
    }
    
    public void contextDestroyed(ServletContextEvent sce)
    {
        // TODO Auto-generated method stub
        
    }
    
}
