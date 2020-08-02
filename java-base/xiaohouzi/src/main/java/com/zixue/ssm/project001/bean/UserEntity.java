package com.zixue.ssm.project001.bean;

import com.zixue.util.jdbc.DataWrapper;

public class UserEntity {
	private String id ;					// id
	private String userName;			//用户姓名
	private String userSax;				//用户性别
	private String userPassword;		//用户密码
	private String userIdno;			//用户身份证号
	private String userMobileNO;		//用户手机号
	private String userAddr;			//用户地址
	private String userNickName;		//用户昵称
	DataWrapper dataWrapper = new DataWrapper();
	public DataWrapper getDataWrapper() {
		return dataWrapper;
	}
	public void setDataWrapper(DataWrapper dataWrapper) {
		this.dataWrapper = dataWrapper;
	}
	public String getId() {
		return dataWrapper.getString(id);
	}
	public void setId(String id) {
		dataWrapper.set("id", id);
	}
	public String getUserName() {
		return  dataWrapper.getString(userName);
	}
	public void setUserName(String userName) {
		dataWrapper.set("userName", userName);
	}
	public String getUserSax() {
		return  dataWrapper.getString(userSax);
	}
	public void setUserSax(String userSax) {
		dataWrapper.set("userSax", userSax);
	}
	public String getUserPassword() {
		return  dataWrapper.getString(userPassword);
	}
	public void setUserPassword(String userPassword) {
		dataWrapper.set("userPassword", userPassword);
	}
	public String getUserIdno() {
		return  dataWrapper.getString(userIdno);
	}
	public void setUserIdno(String userIdno) {
		dataWrapper.set("userIdno", userIdno);
	}
	public String getUserMobileNO() {
		return  dataWrapper.getString(userMobileNO);
	}
	public void setUserMobileNO(String userMobileNO) {
		dataWrapper.set("userMobileNO", userMobileNO);
	}
	public String getUserAddr() {
		return  dataWrapper.getString(userAddr);
	}
	public void setUserAddr(String userAddr) {
		dataWrapper.set("userAddr", userAddr);
	}
	public String getUserNickName() {
		return  dataWrapper.getString(userNickName);
	}
	public void setUserNickName(String userNickName) {
		dataWrapper.set("userNickName", userNickName);
	}
	
}
