package com.ttsc.data.dao.area;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ttsc.data.entity.area.TownInfo;

/**
 * 区县信息
 * @author arno.jiang
 *
 */
@Repository
public interface TownDao {

	/**
	 * 根据城市获取区县
	 * @param cityId
	 * @return
	 */
	public List<TownInfo> getTownList(int cityId);
}
