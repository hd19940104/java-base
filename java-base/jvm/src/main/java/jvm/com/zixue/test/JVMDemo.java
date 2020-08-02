package jvm.com.zixue.test;

import java.util.Random;

public class JVMDemo {
/**
 * 笔记：JVM
 * 
 * jvm体系结构：
 * 		ClassLoader(类加载器):
 * 				启动器加载器 BootStrap
 * 				扩展类加载器 ExtClassLoader
 * 				应用类加载器 AppClassLoader
 * 
 *注：双亲委派机制
 *	 沙箱安全机制
 *
 *native  本地方法栈 
 *JNI	java native interface
 *
 *程序	  程序计数器
 *
 *方法区： 	静态变量+常量+构造方法+接口
 *
 *stack:	栈  	StackOverFlower 栈溢出   基本类型对象+实例对象方法+对象的引用
 *
 *heap;		堆	（逻辑上）
 *		{
 *			新生区	{
 *						伊甸区
 *						幸存区0(flow)
 *						幸存区1(flow)
 *					}
 *			养老区
 *			永久存储区【元空间】
 *
 *		}
 *
 *
 *
 *HotSpot :天上飞的理念-落地就有它的实现
 *
 *
 *GC(分代收集算法)
 *		引用计数法
 *		复制算法 		Copying
 *		标记清除		Mark-Sweep
 *		标记压缩		Mark-Compact
 *		标记清除压缩	Mark-Sweep-Compact
 *
 *
 *
 *
 *
 */
	
	
	byte[] b = new byte[1*1024*1024];
	public static void main(String[] args) {
		 
		java.util.List<JVMDemo> list = new java.util.ArrayList<>();
		int count =0;
		try {
			while(true) {
				
				list.add(new JVMDemo());
				count++;
				
			}
		}catch (Throwable e) {
			System.out.println("***************count:"+count);
			e.printStackTrace();
		}
		
		
		
		
		
/*		Object obj = new Object();
		JVMDemo jvmDemo =new JVMDemo();
		//null
		System.out.println(obj.getClass().getClassLoader());
		//sun.misc.Launcher$AppClassLoader@4e25154f
		System.out.println(jvmDemo.getClass().getClassLoader());
		//sun.misc.Launcher$ExtClassLoader@33909752
		System.out.println(jvmDemo.getClass().getClassLoader().getParent());
		//null
		System.out.println(jvmDemo.getClass().getClassLoader().getParent().getParent());
		
		
		
		
		new Thread().start();
		
		test1();*/
		
		
//		long maxMemory = Runtime.getRuntime().maxMemory() ;//返回 Java 虚拟机试图使用的最大内存量。
//		long totalMemory = Runtime.getRuntime().totalMemory() ;//返回 Java 虚拟机中的内存总量。
//		System.out.println("MAX_MEMORY = " + maxMemory + "（字节）、" + (maxMemory / (double)1024 / 1024) + "MB");
//		System.out.println("TOTAL_MEMORY = " + totalMemory + "（字节）、" + (totalMemory / (double)1024 / 1024) + "MB");

		/*String str = "www.baidu.com" ;
		while(true) 
		{
		str += str + new Random().nextInt(88888888) + new Random().nextInt(999999999) ;
		}
*/
		
		
		
	}
	
	public static void test1() { test2();}
	public static void test2() { /*test2();*/}
	
	
	
	
	
}
