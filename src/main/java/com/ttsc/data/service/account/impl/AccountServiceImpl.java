package com.ttsc.data.service.account.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ttsc.data.dao.account.AccountDao;
import com.ttsc.data.entity.account.Account;
import com.ttsc.data.service.account.AccountService;

@Component("accountService")
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountDao accountDao;

	@Override
	public int saveUser(Account account) {
		accountDao.saveUser(account);
		return account.getId();
	}

	@Override
	public Account getAccountByPhoneAndPwd(Account account) {
		Account obj = accountDao.getAccountByPhoneAndPwd(account);
		return obj;
	}

	@Override
	public int findAccountByPhone(String telephone) {
		int count = accountDao.findAccountByPhone(telephone);
		return count;
	}

	@Override
	public void updateAccountInfo(Account account) {
		accountDao.updateAccountInfo(account);
	}

}