package com.ttsc.data.entity.account;

/**
 * 邀请码
 * @author stone.zhu
 *
 */
public class InvitationCode {

	private int id;
	//用户id
	private int userId;
	//用户类型
	private int type;
	//邀请码
	private String code;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
}
