package com.ttsc.data.service.area.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ttsc.data.dao.area.CityDao;
import com.ttsc.data.entity.area.CityInfo;
import com.ttsc.data.service.area.CityService;

@Component("cityService")
public class CityServiceImpl implements CityService {

	@Autowired
	private CityDao cityDao;

	@Override
	public List<CityInfo> getCityList(int provinceId) {
		return cityDao.getCityList(provinceId);
	}

}
