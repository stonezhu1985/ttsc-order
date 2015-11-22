package com.ttsc.data.service.task.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ttsc.data.dao.task.SpecialTaskDao;
import com.ttsc.data.entity.task.SpecialTask;
import com.ttsc.data.service.task.SpecialTaskService;

@Component("specialTaskService")
public class SpecialTaskServiceImpl implements SpecialTaskService {

	@Autowired
	private SpecialTaskDao specialTaskDao;
	
	@Override
	public int saveTask(SpecialTask task) {
		return specialTaskDao.saveTask(task);
	}

	@Override
	public List<SpecialTask> getTaskListByUserId(int userId) {
		return specialTaskDao.getTaskListByUserId(userId);
	}

}
