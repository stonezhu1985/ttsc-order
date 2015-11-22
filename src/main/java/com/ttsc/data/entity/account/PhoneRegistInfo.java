package com.ttsc.data.entity.account;

import java.util.Date;

/**
 * 手机号码注册信息
 * @author stone.zhu
 *
 */
public class PhoneRegistInfo {
	//id
	private int id;
	//手机号
	private String telephone;
	//创建时间
	private Date createTime;
	//注册时间
	private Date registTime;
	//状态
	private int status;
	//注册码
	private String registCode;
	//0代表手机端、1代表商户pc端
	private int type;
	
	public String getRegistCode() {
		return registCode;
	}
	public void setRegistCode(String registCode) {
		this.registCode = registCode;
	}
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
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getRegistTime() {
		return registTime;
	}
	public void setRegistTime(Date registTime) {
		this.registTime = registTime;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
}
