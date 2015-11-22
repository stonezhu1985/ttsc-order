package com.ttsc.data.service.task;

import java.util.List;

import com.ttsc.data.entity.task.TaskSpecifyDetail;


/**
 * 指定好评
 * @author stone.zhu
 *
 */
public interface TaskSpecifyDetailService {

	/**
	 * 指定好评
	 * @param taskId
	 * @return
	 */
	public List<TaskSpecifyDetail> getTaskSpecifyDetailByTaskId(int taskId);
	
	/**
	 * 保存指定好评任务
	 * @param taskSpecifyDetail
	 */
	public void save(TaskSpecifyDetail taskSpecifyDetail);
}
