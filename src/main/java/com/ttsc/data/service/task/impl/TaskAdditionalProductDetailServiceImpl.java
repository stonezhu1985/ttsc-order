package com.ttsc.data.service.task.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ttsc.data.dao.task.TaskAdditionalProductDetailDao;
import com.ttsc.data.entity.task.TaskAdditionalProductDetail;
import com.ttsc.data.service.task.TaskAdditionalProductDetailService;

@Component("taskAdditionalProductDetailService")
public class TaskAdditionalProductDetailServiceImpl implements
		TaskAdditionalProductDetailService {

	@Autowired
	private TaskAdditionalProductDetailDao  taskAdditionalProductDetailDao;
	
	@Override
	public List<TaskAdditionalProductDetail> getTaskAdditionalProductDetailByTaskId(
			int taskId) {
		// TODO Auto-generated method stub
		return taskAdditionalProductDetailDao.getTaskAdditionalProductDetailByTaskId(taskId);
	}

	@Override
	public void saveTaskAdditionalProductDetail(
			TaskAdditionalProductDetail taskAdditionalProductDetail) {
		// TODO Auto-generated method stub
		taskAdditionalProductDetailDao.saveTaskAdditionalProductDetail(taskAdditionalProductDetail);
	}

}
