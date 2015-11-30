package com.ttsc.data.dao.task;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.ttsc.data.entity.task.TaskShowOrderTimeDetail;

/**
 * 放单时间
 * @author arno.jiang
 *
 */
@Repository
public interface TaskShowOrderTimeDetailDao {

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
