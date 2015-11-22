package com.ttsc.data.service.area;

import java.util.List;

import com.ttsc.data.entity.area.TownInfo;

/**
 * 区县信息服务
 * @author arno.jiang
 *
 */
public interface TownService {
	/**
	 * 根据城市获取区县
	 * @param cityId
	 * @return
	 */
	public List<TownInfo> getTownList(int cityId);
}
