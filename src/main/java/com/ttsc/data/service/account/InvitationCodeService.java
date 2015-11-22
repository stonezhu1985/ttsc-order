package com.ttsc.data.service.account;

import com.ttsc.data.entity.account.InvitationCode;



/**
 * 邀请码service
 * @author stone.zhu
 *
 */
public interface InvitationCodeService {

	/**
	 * 保存邀请码
	 * @param invitationCode 邀请码
	 * @return
	 */
	public int saveInvitationCode(InvitationCode invitationCode);
	
	/**
	 * 根据邀请码查信息
	 * @param code 邀请码
	 * @return
	 */
	public InvitationCode findInvitationByInvitationCode(String code);
	
	/**
	 * 获取最大的maxCode
	 * @return
	 */
	public int findMaxInvitationCode();
}
