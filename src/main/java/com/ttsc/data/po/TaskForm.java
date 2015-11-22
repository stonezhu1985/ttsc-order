package com.ttsc.data.po;

import java.util.List;

import com.ttsc.data.entity.task.Task;
import com.ttsc.data.entity.task.TaskAdditionalProductDetail;
import com.ttsc.data.entity.task.TaskEmptyPacketServiceDetail;
import com.ttsc.data.entity.task.TaskKeyWordDetail;
import com.ttsc.data.entity.task.TaskSpecifyDetail;

public class TaskForm {
	private Task task;
	private List<TaskAdditionalProductDetail> taskAdditionalProductDetailList; // 附加商品
	private TaskEmptyPacketServiceDetail taskEmptyPacketServiceDetail;// 空包服务
	private List<TaskKeyWordDetail> taskKeyWordDetailList;// 关键字
	private List<TaskSpecifyDetail> taskSpecifyDetailList;// 指定好评

	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
	}

	public List<TaskAdditionalProductDetail> getTaskAdditionalProductDetailList() {
		return taskAdditionalProductDetailList;
	}

	public void setTaskAdditionalProductDetailList(
			List<TaskAdditionalProductDetail> taskAdditionalProductDetailList) {
		this.taskAdditionalProductDetailList = taskAdditionalProductDetailList;
	}

	public TaskEmptyPacketServiceDetail getTaskEmptyPacketServiceDetail() {
		return taskEmptyPacketServiceDetail;
	}

	public void setTaskEmptyPacketServiceDetail(
			TaskEmptyPacketServiceDetail taskEmptyPacketServiceDetail) {
		this.taskEmptyPacketServiceDetail = taskEmptyPacketServiceDetail;
	}

	public List<TaskKeyWordDetail> getTaskKeyWordDetailList() {
		return taskKeyWordDetailList;
	}

	public void setTaskKeyWordDetailList(
			List<TaskKeyWordDetail> taskKeyWordDetailList) {
		this.taskKeyWordDetailList = taskKeyWordDetailList;
	}

	public List<TaskSpecifyDetail> getTaskSpecifyDetailList() {
		return taskSpecifyDetailList;
	}

	public void setTaskSpecifyDetailList(
			List<TaskSpecifyDetail> taskSpecifyDetailList) {
		this.taskSpecifyDetailList = taskSpecifyDetailList;
	}
}
