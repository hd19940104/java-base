package com.zixue.wx.pojo;

import lombok.Data;
/**
	obj.put("isBind", true);
	obj.put("name", "插座");
	obj.put("url", "");
	obj.put("icon", "../../../assets/images/iconfont-list-active.png");
	obj.put("code", "11");
 * @author 一只会飞的小猴子
 * @apiNote 设备相关信息
 */
@Data
public class Setting {
	private Boolean isBind;
	private String name;
	private String url;
	private String icon;
	private String code;
}
