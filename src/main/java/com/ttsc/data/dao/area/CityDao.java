package com.ttsc.data.dao.area;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ttsc.data.entity.area.CityInfo;

/**
 * 城市信息
 * @author arno.jiang
 *
 */
@Repository
public interface CityDao {
	/**
	 * 获取城市列表
	 * @return
	 */
	public List<CityInfo> getCityList(int provinceId);
}
