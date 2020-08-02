package com.zixue;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.SimpleFormatter;
public class QQDemo {

	 
	/**
	 * 消息轰炸类
	 * 
	 * @author Administrator
	 *
	 */

	 
	/**
	 * 利用机器人复制粘贴消息然后自动发送
	 * 
	 * @param args
	 * @throws AWTException
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws AWTException, InterruptedException {
		int count = 0;
		// 创建机器人
		Robot robot = new Robot();
		// 机器人点击左键,定位输入光标
		robot.mousePress(KeyEvent.BUTTON1_MASK);
		// 释放左键
		robot.mouseRelease(KeyEvent.BUTTON1_MASK);
		Clipboard clip = Toolkit.getDefaultToolkit().getSystemClipboard();
		
	    SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //格式化为日期/时间字符串
       
		// 自定义发送次数
		for (int i = 0; i < 1000000; i++) {
			System.out.println(count);
			Thread.sleep(100);
			Date date = new Date();
			Transferable tText =new StringSelection("【"+sdf.format(date)+"】在不在？"+"【"+count+++"】");
			clip.setContents(tText, null);
			// 按下Control键
			robot.keyPress(KeyEvent.VK_CONTROL);
			// 按下V键
			robot.keyPress(KeyEvent.VK_V);
			// 释放V键
			robot.keyRelease(KeyEvent.VK_V);
			// 释放Control键
			
			robot.keyRelease(KeyEvent.VK_CONTROL);
			// 按下Enter键,此键对应QQ或微信的发送快捷键,可以自定义快捷键
			robot.keyPress(KeyEvent.VK_ENTER);
			// 释放Enter键
			robot.keyRelease(KeyEvent.VK_ENTER);
			tText = null;
			date = null;
		}
	}

}
