package com.ttsc.data.dao.task;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ttsc.data.entity.task.SpecialTask;

/**
 * 特殊任务
 * @author arno.jiang
 *
 */
@Repository
public interface SpecialTaskDao {

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
