package com.ttsc.data.service.account;

import java.util.List;

import com.ttsc.data.entity.account.UserShopBindInfo;

/**
 * 商户店铺绑定
 * @author arno.jiang
 *
 */
public interface UserShopBindService {
	/**
	 * 保存商户店铺绑定信息
	 * @param userShopBindInfo
	 * @return
	 */
	public int saveUserShopBind(UserShopBindInfo userShopBindInfo);
	
	/**
	 * 根据用户Id获取绑定信息列表
	 * @param userId
	 * @return
	 */
	public List<UserShopBindInfo> getUserShopBindList(int userId);
	
	/**
	 * 获取审批通过的商铺
	 * @param userId
	 * @return
	 */
	public List<UserShopBindInfo> getPassedUserShopBindList(int userId);
}
