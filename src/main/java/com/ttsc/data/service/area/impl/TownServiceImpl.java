package com.ttsc.data.service.area.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ttsc.data.dao.area.TownDao;
import com.ttsc.data.entity.area.TownInfo;
import com.ttsc.data.service.area.TownService;

@Component("townService")
public class TownServiceImpl implements TownService {
	@Autowired
	private TownDao townDao;

	@Override
	public List<TownInfo> getTownList(int cityId) {
		return townDao.getTownList(cityId);
	}

}
