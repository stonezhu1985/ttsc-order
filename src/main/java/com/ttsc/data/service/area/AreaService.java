package com.ttsc.data.service.area;

import java.util.List;

import com.ttsc.data.entity.area.AreaInfo;

public interface AreaService {
	/**
	 * 获取区域列表
	 * @return
	 */
	public List<AreaInfo> getAreaInfoList();
}
