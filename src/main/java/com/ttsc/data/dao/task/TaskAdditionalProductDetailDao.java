package com.ttsc.data.dao.task;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ttsc.data.entity.task.TaskAdditionalProductDetail;

/**
 * 附加商品
 * @author arno.jiang
 *
 */
@Repository
public interface TaskAdditionalProductDetailDao {
	
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
