package com.zixue.ssm.project001.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zixue.ssm.project001.bean.UserEntity;
import com.zixue.ssm.project001.mapper.UserLoginDao;
import com.zixue.ssm.project001.service.UserLoginService;
@Service
public class UserLoginServiceImpl implements UserLoginService {
	@Autowired
	UserLoginDao userLoginDao;
	@Override
	public UserEntity queryUserEntityPageData() {
		UserEntity queryUserEntityPageData = userLoginDao.queryUserEntityPageData();
		return queryUserEntityPageData;
	}

}
