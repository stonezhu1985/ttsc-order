package com.ttsc.data.service.task;

import java.util.List;

import com.ttsc.data.entity.task.TaskKeyWordDetail;

/**
 * 关键字
 * @author arno.jiang
 *
 */
public interface TaskKeyWordDetailService {

	/**
	 * 根据任务id查询关键字
	 * @param taskId
	 * @return
	 */
	public List<TaskKeyWordDetail> getTaskKeyWordDetailByTaskId(int taskId);
	
	/**
	 * 保存任务的关键字
	 * @param taskKeyWordDetail
	 */
	public void saveTaskKeyWordDetail(TaskKeyWordDetail taskKeyWordDetail);
}
