package com.ttsc.data.entity.task;

/**
 * 关键字
 * @author stone.zhu
 *
 */
public class TaskKeyWordDetail {

	//id
	private int id;
	//搜索关键字
	private String keyWord;
	//订单数
	private int orderNum;
	//稀释流量
	private int rate;
	//任务id
	private int taskId;
	//浏览收藏加购数
	private int bcPurchaseNum;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getKeyWord() {
		return keyWord;
	}
	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}
	public int getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(int orderNum) {
		this.orderNum = orderNum;
	}
	public int getRate() {
		return rate;
	}
	public void setRate(int rate) {
		this.rate = rate;
	}
	public int getTaskId() {
		return taskId;
	}
	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}
	public int getBcPurchaseNum() {
		return bcPurchaseNum;
	}
	public void setBcPurchaseNum(int bcPurchaseNum) {
		this.bcPurchaseNum = bcPurchaseNum;
	}
}
