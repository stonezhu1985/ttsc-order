package com.ttsc.data.service.task.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ttsc.data.dao.task.TaskKeyWordDetailDao;
import com.ttsc.data.entity.task.TaskKeyWordDetail;
import com.ttsc.data.service.task.TaskKeyWordDetailService;

@Component("taskKeyWordDetailService")
public class TaskKeyWordDetailServiceImpl implements TaskKeyWordDetailService {

	@Autowired
	private TaskKeyWordDetailDao taskKeyWordDetailDao;
	@Override
	public List<TaskKeyWordDetail> getTaskKeyWordDetailByTaskId(int taskId) {
		// TODO Auto-generated method stub
		return taskKeyWordDetailDao.getTaskKeyWordDetailByTaskId(taskId);
	}

	@Override
	public void saveTaskKeyWordDetail(TaskKeyWordDetail taskKeyWordDetail) {
		// TODO Auto-generated method stub
		taskKeyWordDetailDao.saveTaskKeyWordDetail(taskKeyWordDetail);
	}

}
