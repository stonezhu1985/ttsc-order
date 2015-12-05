package com.ttsc.data.dao.area;

import java.util.List;

import com.ttsc.data.entity.area.AreaInfo;

/**
 * 
 * @author arno.jiang
 *
 */
public interface AreaDao {
	/**
	 * 获取区域列表
	 * @return
	 */
	public List<AreaInfo> getAreaInfoList();
}
