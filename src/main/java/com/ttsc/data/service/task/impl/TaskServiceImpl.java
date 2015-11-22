package com.ttsc.data.service.task.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ttsc.data.dao.task.TaskDao;
import com.ttsc.data.entity.task.Task;
import com.ttsc.data.service.task.TaskService;

@Component("taskService")
public class TaskServiceImpl implements TaskService {

	@Autowired
	private TaskDao taskDao;
	
	@Override
	public List<Task> getTaskByShopIdList(int shopId) {
		// TODO Auto-generated method stub
		return taskDao.getTaskByShopIdList(shopId);
	}

	@Override
	public int saveTask(Task task) {
		// TODO Auto-generated method stub
		taskDao.saveTask(task);
		return task.getId();
	}

	@Override
	public void updateTaskStatus(Task task) {
		// TODO Auto-generated method stub
		taskDao.updateTaskStatus(task);
	}

}
