package com.ttsc.data.service.area;

import java.util.List;

import com.ttsc.data.entity.area.ProvinceInfo;

/**
 * 省份信息服务类
 * 
 * @author arno.jiang
 * 
 */
public interface ProvinceService {
	/**
	 * 获取省份列表
	 * 
	 * @return
	 */
	public List<ProvinceInfo> getProvinceList();
	
	/**
	 * 根据区域Id获取省份信息列表
	 * @param areaId
	 * @return
	 */
	public List<ProvinceInfo> getProvinceListByAreaId(int areaId);
}
