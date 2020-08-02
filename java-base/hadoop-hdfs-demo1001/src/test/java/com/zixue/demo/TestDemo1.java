package com.zixue.demo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.zixue.pojo.Person;

public class TestDemo1 {
	public static void main(String[] args) {
		Person person = new Person();// 创建对象
		person.setName("XXX");
		person.setSex("男");
		person.setAge(25);
		person.setAppearance("高富帅");
		System.out.println("你的男朋友：" + person);
		//正则表达式 邮编
		String re = "^[1-9]{6}$";
		 // 邮箱验证规则
	    String regEx = "[a-zA-Z_]{1,}[0-9]{0,}@(([a-zA-z0-9]-*){1,}\\.){1,3}[a-zA-z\\-]{1,}";
		// Pattern pat = Pattern.compile(re, Pattern.CASE_INSENSITIVE);
		Pattern pattern = Pattern.compile(re);
		Matcher matcher = pattern.matcher("012311");
		// 字符串是否与正则表达式相匹配
		boolean rs = matcher.matches();
		System.out.println(rs);
		
		/**
		 * 一个或多个汉字	^[\u0391-\uFFE5]+$ 
		  	邮政编码	^[1-9]\d{5}$
			QQ号码	^[1-9]\d{4,10}$ 
			邮箱	^[a-zA-Z_]{1,}[0-9]{0,}@(([a-zA-z0-9]-*){1,}\.){1,3}[a-zA-z\-]{1,}$ 
			用户名（字母开头 + 数字/字母/下划线）	^[A-Za-z][A-Za-z1-9_-]+$
			手机号码	^1[3|4|5|8][0-9]\d{8}$ 
			URL	^((http|https)://)?([\w-]+\.)+[\w-]+(/[\w-./?%&=]*)?$ 
			18位身份证号	^(\d{6})(18|19|20)?(\d{2})([01]\d)([0123]\d)(\d{3})(\d|X|x)?$
		 */
	}
}
