package com.ttsc.data.service.task;

import java.util.List;

import com.ttsc.data.entity.task.Task;

/**
 * 任务
 * @author arno.jiang
 *
 */
public interface TaskService {

	/**
	 * 查询商户的task
	 * @param shopId
	 * @return
	 */
	public List<Task> getTaskByShopIdList(int shopId);
	
	/**
	 * 保存任务信息
	 * @param task
	 */
	public int saveTask(Task task);
	
	/**
	 * 更新任务task的状态
	 * @param task
	 */
	public void updateTaskStatus(Task task);
}
