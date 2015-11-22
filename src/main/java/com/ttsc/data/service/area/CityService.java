package com.ttsc.data.service.area;

import java.util.List;

import com.ttsc.data.entity.area.CityInfo;

/**
 * 
 * @author arno.jiang
 *
 */
public interface CityService {
	/**
	 * 获取城市列表
	 * @return
	 */
	public List<CityInfo> getCityList(int provinceId);
}
