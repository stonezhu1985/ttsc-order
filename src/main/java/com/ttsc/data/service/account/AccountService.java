package com.ttsc.data.service.account;

import com.ttsc.data.entity.account.Account;

public interface AccountService {
	/**
	 * 注册商户用户
	 * @param account
	 */
	public int saveUser(Account account);
	
	/**
	 * 根据手机、密码获取商户用户信息
	 * @param account
	 * @return
	 */
	public Account getAccountByPhoneAndPwd(Account account);
	
	/**
	 * 根据手机号码获取账号数量
	 * @param telephone
	 * @return
	 */
	public int findAccountByPhone(String telephone);
	
	/**
	 * 修改商户信息
	 * @param account
	 */
	public void updateAccountInfo(Account account);
}
