package com.zixue.jdk8.Lambda;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

/**
 * 
 * @ClassName LambdaDemo
 * @Description Lambda表达式
 * @author 一只会飞的小猴子
 * @date 2019年8月20日 下午3:49:16 tton
 */
public class LambdaDemo {
	public static void main(String[] args) {
		List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
		for (int i = 0; i < list.size(); i++) {
			System.out.print(list.get(i));
		}
		System.out.println("\n--------");
		for (Integer integer : list) {
			System.out.print(integer);
		}
		System.out.println("\n--------");
		list.forEach(new Consumer<Integer>() {

			@Override
			public void accept(Integer integer) {
				System.out.print(integer);
			}
		});
		System.out.println("\n--------");
		list.forEach((Integer integet) -> {
			System.out.print(integet);
		});
		list.forEach(System.out::print);
		
		System.out.println("\n--------");
		
		
		LambdaDemo demo = new LambdaDemo();

		// 传统
		demo.myTest(new Test() {
			@Override
			public void test() {
				System.out.println("12");
			}
		});
		// Lambda
		demo.myTest(() -> {
			System.out.println("123");
		});
		//demo.myTest(System.out::print);
		System.out.println();

	}

	public void myTest(Test test) {
		test.test();
		System.out.println(test.getClass());
		System.out.println(test.getClass().getInterfaces());
		System.out.println(test.getClass().getInterfaces()[0]);
	}

	Test test = () -> {
		System.out.println("11");
	};// Lambda是对象

}
/**
 * 
 * @ClassName  Test 
 * @Description 方法式接口  接口里面只有一个方法，（可以有重写Object方法）
 * @author 一只会飞的小猴子
 * @date  2019年8月20日 下午5:09:04 
 *
 */
@FunctionalInterface
interface Test {
	void test();
	//void test2();
	String toString();
}
