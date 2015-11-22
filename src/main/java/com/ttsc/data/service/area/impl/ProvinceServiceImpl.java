package com.ttsc.data.service.area.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ttsc.data.dao.area.ProvinceDao;
import com.ttsc.data.entity.area.ProvinceInfo;
import com.ttsc.data.service.area.ProvinceService;

@Component("provinceService")
public class ProvinceServiceImpl implements ProvinceService {
	@Autowired
	private ProvinceDao provinceDao;

	@Override
	public List<ProvinceInfo> getProvinceList() {
		return provinceDao.getProvinceList();
	}

}
