package com.ttsc.data.service.account.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ttsc.data.dao.account.InvitationCodeDao;
import com.ttsc.data.entity.account.InvitationCode;
import com.ttsc.data.service.account.InvitationCodeService;


@Component("invitationCodeService")
public class InvitationCodeServiceImpl implements InvitationCodeService {

	@Autowired
	private InvitationCodeDao invitationCodeDao;
	
	@Override
	public int saveInvitationCode(InvitationCode invitationCode) {
		return invitationCodeDao.saveInvitationCode(invitationCode);
	}

	@Override
	public InvitationCode findInvitationByInvitationCode(String code) {
		return invitationCodeDao.findInvitationByInvitationCode(code);
	}

	@Override
	public int findMaxInvitationCode() {
		return invitationCodeDao.findMaxInvitationCode();
	}

}
