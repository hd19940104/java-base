package com.zixue.jdk8.Lambda;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;


public class LambdaDemo1 {
	public static void main(String[] args) {
		new Thread(() -> {
			System.out.println("this is lambda ");
		}).start();
		List<String> list = Arrays.asList("a","t");
		list.stream().map((item)->item.toUpperCase()).forEach(item->System.out.print(item));
		System.out.println();
		list.stream().map(String::toUpperCase).forEach(item->System.out.print(item));
		System.out.println();
		Function<String, String> function = String:: toUpperCase;
		System.out.println(function.getClass().getInterfaces()[0]);
		
		Collections.sort(list,(l1,l2)->l2.compareTo(l1));
		
	}
}
