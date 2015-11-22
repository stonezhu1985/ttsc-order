package com.ttsc.data.service.account.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ttsc.data.dao.account.PhoneRegistDao;
import com.ttsc.data.entity.account.PhoneRegistInfo;
import com.ttsc.data.service.account.PhoneRegistService;

@Component("phoneRegistService")
public class PhoneRegistServiceImpl implements PhoneRegistService {

	@Autowired
	private PhoneRegistDao phoneRegistDao;

	@Override
	public PhoneRegistInfo findPhoneRegistDaoByPhoneAndRegistCode(
			PhoneRegistInfo phoneRegistInfo) {
		return phoneRegistDao
				.findPhoneRegistDaoByPhoneAndRegistCode(phoneRegistInfo);
	}

	@Override
	public void savePhoneRegistInfo(PhoneRegistInfo phoneRegistInfo) {
		phoneRegistDao.savePhoneRegistInfo(phoneRegistInfo);
	}

	@Override
	public void updatePhoneRegistInfo(PhoneRegistInfo phoneRegistInfo) {
		phoneRegistDao.updatePhoneRegistInfo(phoneRegistInfo);
	}

}
