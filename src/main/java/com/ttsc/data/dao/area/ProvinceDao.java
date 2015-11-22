package com.ttsc.data.dao.area;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ttsc.data.entity.area.ProvinceInfo;

/**
 * 身份信息数据库交互
 * @author arno.jiang
 *
 */
@Repository
public interface ProvinceDao {

	/**
	 * 获取省份列表
	 * @return
	 */
	public List<ProvinceInfo> getProvinceList();
}
