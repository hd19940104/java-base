package com.zixue.test;

public class Father {

	
	public  Father() {
		System.out.println("1111111111");
	}
	static {
		System.out.println("22222222222");
	}
	
public static void main(String[] args) {
		
		
		new Son();
		System.out.println("---------------------");
		new Father();
	}
	
}


class Son extends Father{

	public Son() {
		System.out.println("3333333333");
	}
	static {
		System.out.println("4444444444");
	}
	
	
	
	
}
