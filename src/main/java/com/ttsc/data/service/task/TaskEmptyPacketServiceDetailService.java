package com.ttsc.data.service.task;

import java.util.List;
import com.ttsc.data.entity.task.TaskEmptyPacketServiceDetail;

/**
 * 空包服务
 * @author stone.zhu
 *
 */
public interface TaskEmptyPacketServiceDetailService {

	/**
	 * 根据任务id查询空包任务
	 * @param taskId
	 * @return
	 */
	public List<TaskEmptyPacketServiceDetail> getTaskEmptyPacketServiceDetailByTaskId(int taskId);
	
	/**
	 * 保存空包任务
	 * @param taskEmptyPacketServiceDetail
	 */
	public void saveTaskEmptyPacketServiceDetail(TaskEmptyPacketServiceDetail taskEmptyPacketServiceDetail);
}
