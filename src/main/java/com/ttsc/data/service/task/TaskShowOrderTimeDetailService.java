package com.ttsc.data.service.task;

import java.util.List;

import com.ttsc.data.entity.task.TaskShowOrderTimeDetail;

/**
 * 特殊放单时间服务类
 * @author arno.jiang
 *
 */
public interface TaskShowOrderTimeDetailService {
	/**
	 * 保存放单时间
	 * @param taskShowOrderTimeDetail
	 * @return
	 */
	public int save(TaskShowOrderTimeDetail taskShowOrderTimeDetail);
	
	/**
	 * 根据任务ID获取放单时间
	 * @param taskId
	 * @return
	 */
	public List<TaskShowOrderTimeDetail> getTaskShowOrderTimeDetailByTaskId(int taskId);
}
