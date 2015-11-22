package com.ttsc.data.service.account;

import com.ttsc.data.entity.account.PhoneRegistInfo;

/**
 * 注册码
 * @author stone.zhu
 *
 */
public interface PhoneRegistService {

	/**
	 * 查询手机号，注册码在30分钟以内的
	 * @param phoneRegistInfo
	 * @return
	 */
	public PhoneRegistInfo findPhoneRegistDaoByPhoneAndRegistCode(PhoneRegistInfo phoneRegistInfo);
	
	/**
	 * 保存注册码
	 * @param phoneRegistInfo
	 */
	public void savePhoneRegistInfo(PhoneRegistInfo phoneRegistInfo);
	
	/**
	 * 修改注册码
	 * @param phoneRegistInfo
	 */
	public void updatePhoneRegistInfo(PhoneRegistInfo phoneRegistInfo);
}
