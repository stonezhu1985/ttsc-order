package com.ttsc.data.service.task.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ttsc.data.dao.task.TaskEmptyPacketServiceDetailDao;
import com.ttsc.data.entity.task.TaskEmptyPacketServiceDetail;
import com.ttsc.data.service.task.TaskEmptyPacketServiceDetailService;

@Component("taskEmptyPacketServiceDetailService")
public class TaskEmptyPacketServiceDetailServiceImpl implements
		TaskEmptyPacketServiceDetailService {

	@Autowired
	private TaskEmptyPacketServiceDetailDao taskEmptyPacketServiceDetailDao;
	
	@Override
	public List<TaskEmptyPacketServiceDetail> getTaskEmptyPacketServiceDetailByTaskId(
			int taskId) {
		// TODO Auto-generated method stub
		return taskEmptyPacketServiceDetailDao.getTaskEmptyPacketServiceDetailByTaskId(taskId);
	}

	@Override
	public void saveTaskEmptyPacketServiceDetail(
			TaskEmptyPacketServiceDetail taskEmptyPacketServiceDetail) {
		// TODO Auto-generated method stub
		taskEmptyPacketServiceDetailDao.saveTaskEmptyPacketServiceDetail(taskEmptyPacketServiceDetail);
	}

}
