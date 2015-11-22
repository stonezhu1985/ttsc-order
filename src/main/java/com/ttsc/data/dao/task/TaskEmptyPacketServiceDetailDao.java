package com.ttsc.data.dao.task;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ttsc.data.entity.task.TaskEmptyPacketServiceDetail;

/**
 * 空包任务
 * @author arno.jiang
 *
 */
@Repository
public interface TaskEmptyPacketServiceDetailDao {
	
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
