package com.ttsc.data.service.area.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ttsc.data.dao.area.AreaDao;
import com.ttsc.data.entity.area.AreaInfo;
import com.ttsc.data.service.area.AreaService;

@Component("areaService")
public class AreaServiceImpl implements AreaService {

	@Autowired
	private AreaDao areaDao;
	
	@Override
	public List<AreaInfo> getAreaInfoList() {
		return areaDao.getAreaInfoList();
	}

}
