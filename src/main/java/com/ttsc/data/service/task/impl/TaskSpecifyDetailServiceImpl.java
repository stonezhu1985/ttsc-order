package com.ttsc.data.service.task.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ttsc.data.dao.task.TaskSpecifyDetailDao;
import com.ttsc.data.entity.task.TaskSpecifyDetail;
import com.ttsc.data.service.task.TaskSpecifyDetailService;

@Component("taskSpecifyDetailService")
public class TaskSpecifyDetailServiceImpl implements TaskSpecifyDetailService {

	@Autowired
	private  TaskSpecifyDetailDao  taskSpecifyDetailDao;
	
	@Override
	public List<TaskSpecifyDetail> getTaskSpecifyDetailByTaskId(int taskId) {
		return taskSpecifyDetailDao.getTaskSpecifyDetailByTaskId(taskId);
	}

	@Override
	public void save(TaskSpecifyDetail taskSpecifyDetail) {
		taskSpecifyDetailDao.save(taskSpecifyDetail);
	}

}
