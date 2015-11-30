package com.ttsc.data.service.task.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ttsc.data.dao.task.TaskShowOrderTimeDetailDao;
import com.ttsc.data.entity.task.TaskShowOrderTimeDetail;
import com.ttsc.data.service.task.TaskShowOrderTimeDetailService;

@Component("taskShowOrderTimeDetailService")
public class TaskShowOrderTimeDetailServiceImpl implements
		TaskShowOrderTimeDetailService {

	@Autowired
	private TaskShowOrderTimeDetailDao taskShowOrderTimeDetailDao;
	
	@Override
	public List<TaskShowOrderTimeDetail> getTaskShowOrderTimeDetailByTaskId(
			int taskId) {
		return taskShowOrderTimeDetailDao.getTaskShowOrderTimeDetailByTaskId(taskId);
	}

	@Override
	public int save(TaskShowOrderTimeDetail taskShowOrderTimeDetail) {
		taskShowOrderTimeDetailDao.save(taskShowOrderTimeDetail);
		return taskShowOrderTimeDetail.getId();
	}

}
