package com.ttsc.data.entity.account;

import java.util.Date;

/**
 * 商户用户信息
 * 
 * @author arno.jiang
 * 
 */
public class Account {
	private int id;
	private String telephone;// 手机号码
	private String password;// 密码
	private String qq;// qq
	private int invitationCode;// 邀请码
	private Date regTime;// 注册时间

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public int getInvitationCode() {
		return invitationCode;
	}

	public void setInvitationCode(int invitationCode) {
		this.invitationCode = invitationCode;
	}

	public Date getRegTime() {
		return regTime;
	}

	public void setRegTime(Date regTime) {
		this.regTime = regTime;
	}
}
