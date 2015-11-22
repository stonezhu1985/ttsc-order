package com.ttsc.data.service.task;

import java.util.List;

import com.ttsc.data.entity.task.SpecialTask;

/**
 * 特殊任务服务类
 * @author arno.jiang
 *
 */
public interface SpecialTaskService {
	/**
	 * 保存特殊任务
	 * @param task
	 * @return
	 */
	public int saveTask(SpecialTask task);
	
	/**
	 * 根据商户Id获取特殊任务列表
	 * @param userId
	 * @return
	 */
	public List<SpecialTask> getTaskListByUserId(int userId);
}
