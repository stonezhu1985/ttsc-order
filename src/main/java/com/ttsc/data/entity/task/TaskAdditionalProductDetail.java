package com.ttsc.data.entity.task;

/**
 * 附加商品
 * @author stone.zhu
 *
 */
public class TaskAdditionalProductDetail {
	//id
	private int id;
	//商品主图
	private String commodityPhoto;
	//商品链接
	private String commodityHref;
	//商品金额
	private int commodityPrice;
	//商品数量
	private int commodityOorderNum;
	//任务id
	private int taskId;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCommodityPhoto() {
		return commodityPhoto;
	}
	public void setCommodityPhoto(String commodityPhoto) {
		this.commodityPhoto = commodityPhoto;
	}
	public String getCommodityHref() {
		return commodityHref;
	}
	public void setCommodityHref(String commodityHref) {
		this.commodityHref = commodityHref;
	}
	public int getCommodityPrice() {
		return commodityPrice;
	}
	public void setCommodityPrice(int commodityPrice) {
		this.commodityPrice = commodityPrice;
	}
	public int getCommodityOorderNum() {
		return commodityOorderNum;
	}
	public void setCommodityOorderNum(int commodityOorderNum) {
		this.commodityOorderNum = commodityOorderNum;
	}
	public int getTaskId() {
		return taskId;
	}
	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}
}
