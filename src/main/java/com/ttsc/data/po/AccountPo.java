package com.ttsc.data.po;

import java.util.List;

import com.ttsc.data.entity.account.UserShopBindInfo;

public class AccountPo {
	private int id;
	private String telephone;// 手机号码
	private String qq;// qq
	private String regTime;// 注册时间
	List<UserShopBindInfo> list;

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

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getRegTime() {
		return regTime;
	}

	public void setRegTime(String regTime) {
		this.regTime = regTime;
	}

	public List<UserShopBindInfo> getList() {
		return list;
	}

	public void setList(List<UserShopBindInfo> list) {
		this.list = list;
	}
}
