package com.ttsc.data.service.task;

import java.util.List;

import com.ttsc.data.entity.task.TaskAdditionalProductDetail;

/**
 * 附加商品
 * @author arno.jiang
 *
 */
public interface TaskAdditionalProductDetailService {

	/**
	 * 根据任务id查询附加商品
	 * @param taskId
	 * @return
	 */
	public List<TaskAdditionalProductDetail> getTaskAdditionalProductDetailByTaskId(int taskId);
	
	/**
	 * 保存附加商品
	 */
	public void saveTaskAdditionalProductDetail(TaskAdditionalProductDetail taskAdditionalProductDetail);
}
