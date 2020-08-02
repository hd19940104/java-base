package com.zixue.demo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestDemo {
	/**
	 * 获取字符串的长度，如果有中文，则每个中文字符计为2位
	 * 
	 * @param value
	 *            指定的字符串
	 * @return 字符串的长度
	 */
	public static int length(String value) {
		int valueLength = 0;
		String chinese = "[\u0391-\uFFE5]";
		/* 获取字段值的长度，如果含中文字符，则每个中文字符长度为2，否则为1 */
		for (int i = 0; i < value.length(); i++) {
			/* 获取一个字符 */
			String temp = value.substring(i, i + 1);
			/* 判断是否为中文字符 */
			if (temp.matches(chinese)) {
				/* 中文字符长度为2 */
				valueLength += 2;
			} else {
				/* 其他字符长度为1 */
				valueLength += 1;
			}
		}
		return valueLength;
	}

	public static void main(String[] args) {
		// String addr = "成都市锦江区东升街69号1栋1号";
		// int addrlen = length(addr);
		// System.out.println(addrlen);
		// 判断推荐人，存在储存
		/*
		 * Pattern pattern = Pattern.compile("[0-9]*"); Matcher isNum =
		 * pattern.matcher("123123"); if ( isNum.matches() ) { System.out.println("12");
		 * }else { System.out.println("34"); }
		 */
		// Integer.parseInt("0.006")*10000;

		/*
		 * String price = "0.0001";
		 * 
		 * String newPrice = Integer.toString((int) (Double.parseDouble(price) *
		 * 10000));
		 * 
		 * System.out.println(newPrice);
		 */

		List list = new ArrayList(); // 封装协议标号
		Map<String, Object> map = new HashMap<String, Object>();// 封装股东号协议编号去重

		// 1、参数初始化
		String protocol_flag = "1,3,5,1,2,3";

		// 3、业务处理
		String[] protocol_flags = protocol_flag.split(",");
		for (String string : protocol_flags) {
			// 获取对应的数字字典的val 进行去重

			map.put(string, "");

		}
		// 之后去查询对应的协议编号
		for (Map.Entry<String, Object> entry : map.entrySet()) {
			String code_type = entry.getKey();// 模板协议编号

			list.add(code_type);
		}

		System.out.println(list);

	}
}
