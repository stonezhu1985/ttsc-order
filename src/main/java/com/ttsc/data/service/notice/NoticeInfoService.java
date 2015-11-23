package com.ttsc.data.service.notice;

import java.util.List;

import com.ttsc.data.entity.notice.NoticeInfo;
import com.ttsc.data.po.NoticeQueryPo;

public interface NoticeInfoService {
	
	/**
	 * 根据ID获取公告
	 * @param id
	 * @return
	 */
	public NoticeInfo getNoticeById(int id);
	
	/**
	 * 查询公告
	 * @param query
	 * @return
	 */
	public List<NoticeInfo> queryList(NoticeQueryPo query);
	
	/**
	 * 查询公告总条数
	 * @param query
	 * @return
	 */
	public int queryListCount(NoticeQueryPo query);
}
