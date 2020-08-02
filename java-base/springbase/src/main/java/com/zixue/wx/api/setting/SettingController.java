package com.zixue.wx.api.setting;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zixue.wx.pojo.Setting;

/**
 * @apiNote 微信接口 api 提供用户设备信息
 * @author 一只会飞的小猴子
 */
@RestController
@RequestMapping(value = "/api")
public class SettingController {
	@RequestMapping("/setting")
	public String getSetting() {
		JSONObject obj = new JSONObject();
		JSONArray arr =new JSONArray();
		List<Object> list = new ArrayList<Object>();
		Setting setting = new Setting();
		setting.setCode("11");
		setting.setIcon("../../../assets/images/iconfont-list-active.png");
		setting.setIsBind(true);
		setting.setName("插座");
		setting.setUrl("");
		obj = (JSONObject) JSONObject.toJSON(setting); 
		arr.add(obj);
		setting.setCode("11");
		setting.setIcon("../../../assets/images/iconfont-list-active.png");
		setting.setIsBind(true);
		setting.setName("音响");
		setting.setUrl("");
		obj = (JSONObject) JSONObject.toJSON(setting); 
		arr.add(obj);
		setting.setCode("11");
		setting.setIcon("../../../assets/images/iconfont-list-active.png");
		setting.setIsBind(true);
		setting.setName("灯泡");
		setting.setUrl("");
		obj = (JSONObject) JSONObject.toJSON(setting); 
		arr.add(obj);
		setting.setCode("11");
		setting.setIcon("../../../assets/images/iconfont-list-active.png");
		setting.setIsBind(true);
		setting.setName("空调");
		setting.setUrl("");
		obj = (JSONObject) JSONObject.toJSON(setting); 
		arr.add(obj);
		setting.setCode("11");
		setting.setIcon("../../../assets/images/iconfont-plus-circle.png");
		setting.setIsBind(true);
		setting.setName("添加");
		setting.setUrl("");
		obj = (JSONObject) JSONObject.toJSON(setting); 
		arr.add(obj);
		return arr.toJSONString();
	}

}
