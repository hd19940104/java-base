package com.zixue;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.KeyEvent;
import java.io.IOException;

import javax.swing.ImageIcon;
public class QQTest {

	public static void main(String[] args) throws AWTException{
		Robot robot = new Robot();
		// 创建Robot对象
		robot.delay(3000);// 延迟三秒，主要是为了预留出打开窗口的时间，括号内的单位为毫秒
		Image image=new ImageIcon("F:\\data\\1.jpg").getImage();//这里是获取图片，图片路径自己确定
		setClipboardImage(image);
		int count = 0;
		for (int j = 0; j < 10000;j++) {//循环次数
				// 以下两行按下了ctrl+v，完成粘贴功能
				robot.keyPress(KeyEvent.VK_CONTROL);
				robot.keyPress(KeyEvent.VK_V);

				robot.keyRelease(KeyEvent.VK_CONTROL);// 释放ctrl按键，像ctrl，退格键，删除键这样的功能性按键，在按下后一定要释放，不然会出问题。crtl如果按住没有释放，在按其他字母按键是，敲出来的回事ctrl的快捷键。
				robot.delay(400);// 延迟一秒再发送，不然会一次性全发布出去，因为电脑的处理速度很快，每次粘贴发送的速度几乎是一瞬间，所以给人的感觉就是一次性发送了全部。这个时间可以自己改，想几秒发送一条都可以
				robot.keyPress(KeyEvent.VK_ENTER);// 回车 
				// }
				
				System.out.println(count++);
			}
	}
	public static void setClipboardImage(final Image image) {
		Transferable trans = new Transferable() {
			public DataFlavor[] getTransferDataFlavors() {
				return new DataFlavor[] { DataFlavor.imageFlavor };
			}
 
			public boolean isDataFlavorSupported(DataFlavor flavor) {
				return DataFlavor.imageFlavor.equals(flavor);
			}
 
			public Object getTransferData(DataFlavor flavor)
					throws UnsupportedFlavorException, IOException {
				if (isDataFlavorSupported(flavor))
					return image;
				throw new UnsupportedFlavorException(flavor);
			}
 
		};
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(trans,
				null);
	}




}
