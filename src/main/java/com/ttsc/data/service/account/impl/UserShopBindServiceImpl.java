package com.ttsc.data.service.account.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ttsc.data.dao.account.UserShopBindDao;
import com.ttsc.data.entity.account.UserShopBindInfo;
import com.ttsc.data.service.account.UserShopBindService;

@Component("userShopBindService")
public class UserShopBindServiceImpl implements UserShopBindService {
	
	@Autowired
	private UserShopBindDao userShopBindDao;

	@Override
	public List<UserShopBindInfo> getUserShopBindList(int userId) {
		return userShopBindDao.getUserShopBindList(userId);
	}

	@Override
	public int saveUserShopBind(UserShopBindInfo userShopBindInfo) {
		userShopBindDao.saveUserShopBind(userShopBindInfo);
		return userShopBindInfo.getId();
	}

	@Override
	public List<UserShopBindInfo> getPassedUserShopBindList(int userId) {
		return userShopBindDao.getPassedUserShopBindList(userId);
	}
	

}
