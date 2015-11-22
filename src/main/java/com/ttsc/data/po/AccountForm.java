package com.ttsc.data.po;

public class AccountForm {
	private String telephone;
	private String password;
	private String surePwd;
	private String invitationCode;// 邀请码
	private String validCode;// 验证码
	private String phoneValidCode;
	private String oldPwd;

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

	public String getSurePwd() {
		return surePwd;
	}

	public void setSurePwd(String surePwd) {
		this.surePwd = surePwd;
	}

	public String getInvitationCode() {
		return invitationCode;
	}

	public void setInvitationCode(String invitationCode) {
		this.invitationCode = invitationCode;
	}

	public String getValidCode() {
		return validCode;
	}

	public void setValidCode(String validCode) {
		this.validCode = validCode;
	}

	public String getPhoneValidCode() {
		return phoneValidCode;
	}

	public void setPhoneValidCode(String phoneValidCode) {
		this.phoneValidCode = phoneValidCode;
	}

	public String getOldPwd() {
		return oldPwd;
	}

	public void setOldPwd(String oldPwd) {
		this.oldPwd = oldPwd;
	}
}
