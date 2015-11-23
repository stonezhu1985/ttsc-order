package com.ttsc.data.service.notice.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ttsc.data.dao.notice.NoticeInfoDao;
import com.ttsc.data.entity.notice.NoticeInfo;
import com.ttsc.data.po.NoticeQueryPo;
import com.ttsc.data.service.notice.NoticeInfoService;

@Component("noticeInfoService")
public class NoticeInfoServiceImpl implements NoticeInfoService {

	@Autowired
	NoticeInfoDao noticeInfoDao;
	
	@Override
	public NoticeInfo getNoticeById(int id) {
		// TODO Auto-generated method stub
		return noticeInfoDao.getNoticeById(id);
	}

	@Override
	public List<NoticeInfo> queryList(NoticeQueryPo query) {
		// TODO Auto-generated method stub
		return noticeInfoDao.queryList(query);
	}

	@Override
	public int queryListCount(NoticeQueryPo query) {
		// TODO Auto-generated method stub
		return noticeInfoDao.queryListCount(query);
	}
	
}
