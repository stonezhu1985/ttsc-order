package com.ttsc.data.entity.task;

import java.util.Date;

/**
 * 特殊任务
 * 
 * @author arno.jiang
 * 
 */
public class SpecialTask {
	private int id;
	private int platId;// 平台
	private int taskType;// 任务类型
	private String otherPlat;// 其它平台
	private String otherTask;// 其它任务
	private int taskNum;// 任务数量
	private int unitPrice;// 客单价
	private String taskDesc;// 任务特殊说明
	private int userId;
	private Date createTime;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPlatId() {
		return platId;
	}

	public void setPlatId(int platId) {
		this.platId = platId;
	}

	public int getTaskType() {
		return taskType;
	}

	public void setTaskType(int taskType) {
		this.taskType = taskType;
	}

	public String getOtherPlat() {
		return otherPlat;
	}

	public void setOtherPlat(String otherPlat) {
		this.otherPlat = otherPlat;
	}

	public String getOtherTask() {
		return otherTask;
	}

	public void setOtherTask(String otherTask) {
		this.otherTask = otherTask;
	}

	public int getTaskNum() {
		return taskNum;
	}

	public void setTaskNum(int taskNum) {
		this.taskNum = taskNum;
	}

	public int getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(int unitPrice) {
		this.unitPrice = unitPrice;
	}

	public String getTaskDesc() {
		return taskDesc;
	}

	public void setTaskDesc(String taskDesc) {
		this.taskDesc = taskDesc;
	}
}
