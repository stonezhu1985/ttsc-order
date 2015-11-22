package com.ttsc.data.entity.task;

/**
 * 空包服务
 * @author stone.zhu
 *
 */
public class TaskEmptyPacketServiceDetail {
	//id
	private int id;
	//包裹重量
	private int packageWeight;
	//网店编号
	private String dotNumber;
	//任务id
	private int taskId;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPackageWeight() {
		return packageWeight;
	}
	public void setPackageWeight(int packageWeight) {
		this.packageWeight = packageWeight;
	}
	public String getDotNumber() {
		return dotNumber;
	}
	public void setDotNumber(String dotNumber) {
		this.dotNumber = dotNumber;
	}
	public int getTaskId() {
		return taskId;
	}
	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}
	
}
