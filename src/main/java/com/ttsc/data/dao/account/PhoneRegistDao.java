package com.ttsc.data.dao.account;

import org.springframework.stereotype.Repository;

import com.ttsc.data.entity.account.PhoneRegistInfo;

/**
 *注册码dao 
 * @author stone.zhu
 *
 */
@Repository
public interface PhoneRegistDao {

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
